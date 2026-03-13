#!/usr/bin/env python3
"""
Get publication year and month from DOI using Crossref API.

Usage examples:
    python3 getPublicationYearAndMonthFromDoi.py "10.1038/s41586-021-03819-2"
    python3 getPublicationYearAndMonthFromDoi.py "10.1038/s41586-021-03819-2" --date-type online
    python3 getPublicationYearAndMonthFromDoi.py "10.1038/s41586-021-03819-2" --date-type print --format json
    python3 getPublicationYearAndMonthFromDoi.py "https://doi.org/10.1038/s41586-021-03819-2" --verbose
"""

import argparse
import json
import sys
from typing import Dict, List, Optional, Tuple

import requests


class CrossrefClient:
    """Client for fetching publication metadata from Crossref."""

    def __init__(self, timeout: int = 30, verbose: bool = False):
        self.base_url = "https://api.crossref.org/works"
        self.timeout = timeout
        self.verbose = verbose
        self.session = requests.Session()
        self.session.headers.update({
            "User-Agent": "PaperNetwork/1.0 (https://github.com/example/papernetwork)"
        })
        self.date_field_map: Dict[str, str] = {
            "online": "published-online",
            "print": "published-print",
            "issued": "issued",
            "posted": "posted",
            "created": "created",
            "deposited": "deposited",
            "indexed": "indexed",
        }
        # Default order to choose the most publication-like date first.
        self.default_order: List[str] = [
            "published-online",
            "published-print",
            "issued",
            "posted",
            "created",
            "deposited",
            "indexed",
        ]

    def _clean_doi(self, doi: str) -> str:
        """Normalize DOI by removing URL/prefix artifacts."""
        return doi.replace("https://doi.org/", "").replace("http://doi.org/", "").replace("doi:", "").strip()

    def _extract_date(self, item: Dict, field: str) -> Tuple[Optional[int], Optional[int], List[int]]:
        """Extract year/month from a Crossref date field."""
        date_parts = item.get(field, {}).get("date-parts", [[]])
        if not date_parts or not date_parts[0]:
            return None, None, []
        parts = date_parts[0]
        year = parts[0] if len(parts) > 0 else None
        month = parts[1] if len(parts) > 1 else None
        return year, month, parts

    def _format_year_month(self, year: int, month: Optional[int]) -> str:
        """Format year and month as YYYY-MM when possible."""
        if month:
            return f"{int(year):04d}-{int(month):02d}"
        return f"{int(year):04d}"

    def get_publication_year_month(self, doi: str, date_type: str = "best") -> Tuple[Optional[Dict], Optional[str]]:
        """Fetch publication year/month for a DOI."""
        try:
            clean_doi = self._clean_doi(doi)

            if self.verbose:
                print(f"Requesting metadata for DOI: {clean_doi}", file=sys.stderr)

            url = f"{self.base_url}/{clean_doi}"
            response = self.session.get(url, timeout=self.timeout)

            if self.verbose:
                print(f"Crossref status: {response.status_code}", file=sys.stderr)

            response.raise_for_status()
            data = response.json()

            if "message" not in data:
                return None, "Unexpected response structure from Crossref"

            item = data["message"]

            if date_type == "best":
                fields_to_try = self.default_order
            else:
                mapped_field = self.date_field_map.get(date_type)
                if not mapped_field:
                    return None, f"Unsupported date type: {date_type}"
                fields_to_try = [mapped_field]

            tried_fields = []
            for field in fields_to_try:
                tried_fields.append(field)
                year, month, parts = self._extract_date(item, field)
                if year:
                    formatted = self._format_year_month(year, month)
                    return {
                        "doi": clean_doi,
                        "date_type_requested": date_type,
                        "date_field_used": field,
                        "formatted_date": formatted,
                        "year": year,
                        "month": month,
                        "raw_date_parts": parts,
                        "title": item.get("title", [""])[0] if item.get("title") else "",
                        "journal": item.get("container-title", [""])[0] if item.get("container-title") else "",
                        "available_date_fields": [f for f in self.default_order if item.get(f)]
                    }, None

            return None, f"No date found for requested type. Tried fields: {', '.join(tried_fields)}"

        except requests.exceptions.RequestException as e:
            return None, f"Network error: {e}"
        except json.JSONDecodeError as e:
            return None, f"JSON parsing error: {e}"
        except Exception as e:
            return None, f"Unexpected error: {e}"


def build_parser() -> argparse.ArgumentParser:
    parser = argparse.ArgumentParser(
        description="Get publication year and month from DOI using Crossref API",
        formatter_class=argparse.RawDescriptionHelpFormatter,
        epilog="""Examples:
  %(prog)s "10.1038/s41586-021-03819-2"
  %(prog)s "10.1038/s41586-021-03819-2" --date-type online
  %(prog)s "10.1038/s41586-021-03819-2" --date-type print --format json
  %(prog)s "https://doi.org/10.1038/s41586-021-03819-2" --verbose"""
    )
    parser.add_argument("doi", help="DOI to fetch publication date for")
    parser.add_argument(
        "--date-type",
        choices=["best", "online", "print", "issued", "posted", "created", "deposited", "indexed"],
        default="best",
        help=(
            "Date kind to return:\n"
            "  best      : prefer online > print > issued > posted > created > deposited > indexed\n"
            "  online    : published-online (early view)\n"
            "  print     : published-print (journal issue)\n"
            "  issued    : issued (official publication date Crossref assigns)\n"
            "  posted    : posted (preprint/posted date)\n"
            "  created   : Crossref record created\n"
            "  deposited : deposited with Crossref\n"
            "  indexed   : indexed by Crossref"
        )
    )
    parser.add_argument(
        "--format",
        choices=["text", "json"],
        default="text",
        help="Output format (default: text)",
    )
    parser.add_argument(
        "--output",
        help="Write result to file instead of stdout",
    )
    parser.add_argument(
        "--verbose",
        "-v",
        action="store_true",
        help="Enable verbose output",
    )
    parser.add_argument(
        "--timeout",
        type=int,
        default=30,
        help="Request timeout in seconds (default: 30)",
    )
    return parser


def format_output(result: Dict, output_format: str, verbose: bool) -> str:
    if output_format == "json":
        return json.dumps(result, indent=2, ensure_ascii=False)

    if verbose:
        lines = [
            f"Date: {result['formatted_date']}",
            f"DOI: {result['doi']}",
            f"Date type requested: {result['date_type_requested']}",
            f"Date field used: {result['date_field_used']}",
        ]
        if result.get("title"):
            lines.append(f"Title: {result['title']}")
        if result.get("journal"):
            lines.append(f"Journal: {result['journal']}")
        if result.get("available_date_fields"):
            fields = ", ".join(result["available_date_fields"])
            lines.append(f"Available date fields: {fields}")
        return "\n".join(lines)

    return result["formatted_date"]


def main():
    parser = build_parser()
    args = parser.parse_args()

    client = CrossrefClient(timeout=args.timeout, verbose=args.verbose)
    result, error = client.get_publication_year_month(args.doi, args.date_type)

    if error:
        print(f"Error: {error}", file=sys.stderr)
        sys.exit(1)

    output = format_output(result, args.format, args.verbose)

    if args.output:
        try:
            with open(args.output, "w", encoding="utf-8") as f:
                f.write(output)
            if args.verbose:
                print(f"Saved output to {args.output}", file=sys.stderr)
        except Exception as e:
            print(f"Error writing to file: {e}", file=sys.stderr)
            sys.exit(1)
    else:
        print(output)


if __name__ == "__main__":
    main()
