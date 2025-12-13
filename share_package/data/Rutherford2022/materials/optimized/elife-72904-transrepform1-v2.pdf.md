![Image](./elife-72904-transrepform1-v2_artifacts/image_000000_1ca51564b396bc48aed5d101e78809355986c5456807a799171bb6fd6986b988.png)

## eLife 's transparent reporting form

We encourage authors to provide detailed information within their submission to facilitate the interpretation and replication of experiments. Authors can upload supporting documentation to indicate the use of appropriate reporting guidelines for health-related research (see EQUATOR Network), life science research (see the BioSharing Information Resource), or the ARRIVE guidelines for reporting work involving animal research. Where applicable, authors should refer to any relevant reporting standards documents in this form.

If you have any questions, please consult our Journal Policies and/or contact us: editorial@elifesciences.org.

## Sample-size estimation

- You should state whether an appropriate sample size was computed when the study was being designed
- You should state the statistical method of sample size computation and any required assumptions
- If no explicit power analysis was used, you should describe how you decided what sample (replicate) size (number) to use

Please outline where this information can be found within the submission (e.g., sections or figure legends), or explain why this information doesn't apply to your submission:

The sample size is stated in the abstract, in Table 1, and described in detail in Appendix 0 Tables 2-5.

## Replicates

- You should report how often each experiment was performed
- You should include a definition of biological versus technical replication
- The data obtained should be provided and sufficient information should be provided to indicate the number of independent biological and/or technical replicates
- If you encountered any outliers, you should describe how these were handled
- Criteria for exclusion/inclusion of data should be clearly stated
- High-throughput sequence data should be uploaded before submission, with a private link for reviewers provided (these are available from both GEO and ArrayExpress)

Please outline where this information can be found within the submission (e.g., sections or figure legends), or explain why this information doesn't apply to your submission:

Criteria for inclusion are stated in the Methods &amp; Materials section (lines 194-205). We replicate the intial full sample model in a subset of manually quality checked data (mQC). This is described in Table 1.

## Statistical reporting

- Statistical analysis methods should be described and justified
- Raw data should be presented in figures whenever informative to do so (typically when N per group is less than 10)
- For each experiment, you should identify the statistical tests used, exact values of N, definitions of center, methods of multiple test correction, and dispersion and precision measures (e.g., mean, median, SD, SEM, confidence intervals; and, for the major substantive results, a measure of effect size (e.g., Pearson's r, Cohen's d)
- Report exact p-values wherever possible alongside the summary statistics and 95% confidence intervals. These should be reported for all key questions and not only when the p-value is less than 0.05.

Please outline where this information can be found within the submission (e.g., sections or figure legends), or explain why this information doesn't apply to your submission:

Individual data points are shown in Figures 1 C-D. Other plots of age distributions and evaluation metrics are presented as histograms. The raw data for the evaluation metrics is displayed for every data point in the supplemental tables and also as CSV files in our GitHub repository. Mathmatical modeling details and statistical tests are described in the Methods &amp; Materials section (lines 28-26).

(For large datasets, or papers with a very large number of statistical tests, you may upload a single table file with tests, Ns, etc., with reference to sections in the manuscript.)

## Group allocation

- Indicate how samples were allocated into experimental groups (in the case of clinical studies, please specify allocation to treatment method); if randomization was used, please also state if restricted randomization was applied
- Indicate if masking was used during group allocation, data collection and/or data analysis

Please outline where this information can be found within the submission (e.g., sections or figure legends ), or explain why this information doesn't apply to your submission:

Data in this work was re-used from previous studies and combined from multiple sites. Per site sample descriptions are included in the supplement (Appendix 0 Tables 2-5), along with references to the original data collection publications. Inclusion criteria into the reference cohort was based on available data required (brain MRI and basic demographics). If clinical labels were available for a study, only healthy controls were selected for the training set and patients were put in the clinical test set. However, clinical labels were not available for all studies, thus some undiagnosed medical conditions may be unaccounted for in the sample. There were no experimental groups, besides training and testing splits, which were randomized. The manual quality checked data set was chosen based on available data when embarking on the task of manual QC (in December 2020).

## Additional data files ('source data')

- We encourage you to upload relevant additional data files, such as numerical data that are represented as a graph in a figure, or as a summary table
- Where provided, these should be in the most useful format, and they can be uploaded as 'Source data' files linked to a main figure or table
- Include model definition files including the full list of parameters used
- Include code used for data analysis (e.g., R, MatLab)
- Avoid stating that data files are 'available upon request'

Please indicate the figures or tables for which source data files have been provided:

