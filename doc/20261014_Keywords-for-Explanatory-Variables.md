# Keywords for Normalization: Explanatory Variables

Version: 202601014

## 再検討の記録

122のNormative Modeling (NM) 研究における Explanatory Variables について、
高松案（Table 1 のExplanatory Variables列）として、以下の32個のキーワードで表現することが提案された。

| Main Category         | Minor Category               | Full Spelling                    | Description                                                                                                                                    |
|-----------------------|------------------------------|----------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------|
| Age-related           | Age                          |                                  | Age as a linear age term                                                                                                                       |
| Age-related           | s(age)                       | Smooth function of age           | a nonlinear age effect, implemented via spline basis functions (e.g. in GAM/GAMLSS) or implicitly                                              |
| Age-related           | Age²                         | Age squared                      | Age as a nonlinear age effect, implemented via spline basis functions (e.g. in GAM/GAMLSS) or implicitly through nonlinear kernels (e.g. GPR). |
| Age-related           | Age (polynomial)             |                                  |                                                                                                                                                |
| Age-related           | Age (fractional polynomials) |                                  |                                                                                                                                                |
| Demographics          | Sex                          |                                  |                                                                                                                                                |
| Demographics          | Race                         |                                  |                                                                                                                                                |
| Demographics          | Ethnic background            |                                  |                                                                                                                                                |
| Demographics          | Education                    |                                  |                                                                                                                                                |
| Interactions          | Age×sex / Sex×age            |                                  |                                                                                                                                                |
| Site/Scanner          | Site                         |                                  |                                                                                                                                                |
| Site/Scanner          | Scanner                      |                                  |                                                                                                                                                |
| Site/Scanner          | Scanner vendor               |                                  |                                                                                                                                                |
| Site/Scanner          | Magnetic field strength      |                                  |                                                                                                                                                |
| Site/Scanner          | FreeSurfer version           |                                  |                                                                                                                                                |
| Site/Scanner          | Scanning protocol            |                                  |                                                                                                                                                |
| Site/Scanner          | Acquisition/task parameters  |                                  |                                                                                                                                                |
| Global brain measures | ICV                          | Intracranial volume              |                                                                                                                                                |
| Global brain measures | TIV                          | Total intracranial volume        |                                                                                                                                                |
| Global brain measures | Total brain volume           |                                  |                                                                                                                                                |
| Global brain measures | Mean CT                      | Mean cortical thickness          |                                                                                                                                                |
| Global brain measures | Mean SA                      | Mean surface area                |                                                                                                                                                |
| Image/Data quality    | Euler number                 |                                  |                                                                                                                                                |
| Image/Data quality    | Image quality                |                                  |                                                                                                                                                |
| Image/Data quality    | Mean FD                      | Mean framewise displacement      |                                                                                                                                                |
| Image/Data quality    | Mean relative motion         |                                  |                                                                                                                                                |
| Image/Data quality    | Head motion                  |                                  |                                                                                                                                                |
| Other                 | Hemisphere                   |                                  |                                                                                                                                                |
| Other                 | BMI                          | Body mass index                  |                                                                                                                                                |
| Other                 | FIQ                          | Full-scale intelligence quotient |                                                                                                                                                |
| Other                 | Task performance             |                                  |                                                                                                                                                |
| Other                 | None                         |                                  |                                                                                                                                                |

続いて、 上田が、これらに当てはまらないようなキーワードがないかを再考した。
方法としては、

`share_package/data/<AuthorYear>/DE/json/DE_AuthorYear>_by_codex_<ProcessedTime>.json`,
`share_package/data/<AuthorYear>/DE/json/DE_AuthorYear>_by_claude_<ProcessedTime>.json`,
`share_package/data/<AuthorYear>/DE/json/DE_AuthorYear>_by_gemini_<ProcessedTime>.json`:

というファイルの
"normative_modeling_part/nm5_predictor_variables/answer"
要素の値をキーワードに置き換えて正規化しようとしたときに、
上記32キーワードでは足りないものがないかを考えた。
その結果以下が挙げられた。




## Supplementary Explanation: s(age)

## Supplementary Explanation: Age^2

## Supplementary Explanation: Age (polynominal)

## Supplementary Explanation: Age (fractional polynominals)
