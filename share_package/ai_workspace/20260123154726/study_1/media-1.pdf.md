## Supplementary material

Kobbersmed et al, 2025: Normative modelling of brain function abnormalities in complex pathology needs the whole brain

## Supplementary text

## Supplementary description of the covariate-assisted principal regression model

In our paper, we introduce FUNCOIN for regression and normative modelling of FC matrices. The framework is based on an adaptation of a newly proposed covariance regression method, covariate-assisted principal regression, which was briefly described in Methods. We here provide a more in-depth presentation and refer to the original paper for details, optimization algorithms, proofs of asymptotic properties, and more 1 .

The method builds on common principal components analysis (CPCA) 2-4 , which identifies principal components that are shared among a set of p x p matrices. Like CPCA, covariate-assisted principal regression also identifies components that are shared among all subjects' covariance matrices but identifies components whose strengths depend maximally on covariates (e.g. sex and age). Like in PCA, the components are identified sequentially. In our paper, we use the first two directions identified, i.e. each individual FC matrix gives rise to a 2dimensional point, (u 1 , u 2 ) (Fig. 1B).

The model is a log-linear, heteroscedastic model for covariance matrices for subject i=1…N. Assume that 𝒚 𝒊𝒕 ∈ ℝ # , the measured data for subject i at time point t, follows a p -dimensional multivariate normal distribution with covariance matrix 𝚺 𝒊 . Each subject i has T i observations of 𝒚 𝒊𝒕 . It is assumed that there exists at least one projection, 𝜸 ∈ ℝ # , such that the normally distributed (1-dimensional) variable, 𝑠 $% ≔𝜸 &amp; 𝒚 𝒊𝒕 , satisfies

<!-- formula-not-decoded -->

where 𝛽 ) ∈ ℝ is the intercept, 𝜷 ∈ ℝ *+, are model coefficients, and 𝒙 𝒊 ∈ ℝ *+, is the vector of q-1 covariates of subject i . The identified 𝜸 is scaled to unit Euclidian norm, and the logarithm ensures positive-definiteness of model-predicted FC. Several 𝜸 (at most p components, but limited by the actual number of components shared among the subjects), can be identified sequentially, where each 𝜸 𝒋 is identified after removing the first j-1 components ( Γ .+, = (𝛾 , , … , 𝑦 .+, ) from all subjects' data (see below). This is done by removing the component on time series level by

<!-- formula-not-decoded -->

The consequence of removing the first j-1 components on the covariance matrix is projection deflation, i.e.:

<!-- formula-not-decoded -->

The covariance matrix of 𝒀 &lt; 𝒊 (𝒋) is not of full rank, which is required in the optimization algorithm. Therefore, a rank-completion step is carried out before identifying the next component (see below). See 5 for more details and proofs.

Note that for p&gt;T max , the problem is ill-posed, because the covariance matrices are rankdeficient (although this is not the case in this study). For this high-dimensional case, a variant of the covariate-assisted principal regression method has been developed, where a shrinkage estimator similar to the Ledoit-Wolf estimator 6 is varying among the subjects in a covariatedependent way 5 .

Other versions of covariate-assisted principal regression exist for cases where the covariates are high-dimensional, i.e. q&gt;N 7 ; 𝜸 is time-invariant but the model coefficients change over time 8 ; and for covariance-on-covariance regression, where the predictors are also covariance matrices 9 .

## Correction of the rank-completion step after removal of identified components

In 10 , a rank-completion step is carried out after removing already identified components. As described above, the original proposal is to remove the identified components on time series level by

<!-- formula-not-decoded -->

The covariance matrix of 𝒀 &lt; 𝒊 (𝒋) is not full rank. It was suggested to carry out singular value decomposition (SVD) such that

<!-- formula-not-decoded -->

where U consists of the eigenvectors of the covariance matrix (without scaling with number of time points), and the diagonal matrix D contains the square root of the associated eigenvalues. After removing j components, the j lowest values in D will be zero. The original suggestion is to replace each of these zeros with the H𝑒𝑥𝑝(𝛽 ( ) associated with each component and computing the rank-completed time series data as

<!-- formula-not-decoded -->

However, when having removed 2 or more components, there is no guarantee that the associated vectors in U will be the actual components removed, but only that they span the space of the removed components (of dimension &gt;1). Instead, we complete the rank by adding the values on covariance matrix level, by computing

<!-- formula-not-decoded -->

where 𝐛 ∈ ℝ .+, is the vector of 𝑒𝑥𝑝(𝛽 ( ) values for all the removed components.

## Selecting the number of components with average deviation from diagonality

In the original paper proposing covariate-assisted principal regression 1 , a metric for the average 'deviation from diagonal' 2 is used for model selection. We explain it here, because it is included in the FUNCOIN Python package and is used when reproducing the simulation study from 1 . For a symmetric, positive definite matrix, the deviation from diagonality is defined as

<!-- formula-not-decoded -->

Keeping the diagonal elements constant, this measure increases for increasing values of the offdiagonal elements. A diagonal matrix has a deviation from diagonality of 1.

In FUNCOIN, we sequentially identify components shared among all the subjects' covariance matrices. If the identified components, Γ = (𝛾 , , … , 𝛾 . ) , were precisely eigenvectors of all subjects' covariance matrices, the transformation of each covariance matrix, Γ 4 Σ ' Γ , would result in diagonal matrices. Calculating the average deviation from diagonality

<!-- formula-not-decoded -->

gives us a measure of 'how diagonal' the transformed covariance matrices on average are and thus indicates whether the identified components are shared among the subjects. By calculating DfD sequentially by adding one component at a time, this measure can be used for identifying how many components to keep, since we expect an increase in DfD, if the latest identified component is not shared among the subjects' covariance matrices. Based on simulations, Zhao et al also suggest a threshold of 2 on the deviation from diagonality 1 . Exceeding the threshold or observing a sudden increase in average DfD value can be used for selecting the number of components. In the FUNCOIN python package, this measure is automatically calculated on the training data and stored when fitting the model and can be calculated on out-of-sample data as well.

## Assessing potential scanning site effects

An important sanity check, for both method and data, is the influence of the scanning site, which we do not want to drive our predictions. Our training data comes from 4 different scanning sites. In line with earlier normative modelling studies on structural MRI from UKB 11,12 , we found no scanning site effects on the Z -scores of the healthy subjects (Supplementary Fig. 6), which is probably due to good hardware and acquisition parameter alignment in UKB 13 . The flexibility of our model makes it easy to implement site effects in the model. We retrained the model with scanner site as an additional (categorical, dummy-coded) covariate (i.e. a model with sex, age, sex-age interaction, scanning site) and compared it to the estimates of the basic model presented in Results (sex, age, sex-age interaction). This led to the same 𝜸 𝟏 , 𝜸 𝟐 , 𝛽 ( , and 𝜷 . Identifying a third component in both models led to discrepancy because 𝜸 𝟑 in the model with site effects depended to some extend on scanning site (Supplementary Tables 2-3). That is, FUNCOIN allows us to either account for site effects by including them in the model or focusing on components unaffected by the choice of site (as in this study).

## Simulation study

In order to assess bias and variance on 𝜸 , 𝛽 ( and 𝜷 , we reproduced the simulation study reported in main results in the covariate-assisted principal regression paper 1 . In brief, we simulate a scenario with p =5 and q = 2 (including intercept). For the simulation, we define a 5-by-5 matrix, Γ , whose columns are the 5 true eigenvectors of the covariance matrices, such that Σ $ = ΓΛ ' Γ 5 . Λ ' is a diagonal matrix whose elements are the strengths/eigenvalues of each eigenvector for subject i . Two of the eigenvalues depend on a binary, categorical covariate. The binary variable is simulated from a Bernoulli distribution with probability 0.5, and the eigenvalue is defined as 𝜆 .,$ = exp	(𝑥 $ 𝛽 . ) . From this, we simulate multivariate normal time series ( p =5) for 100 time points for 100 subjects. We repeat the simulation 200 times. In each iteration, we estimate 𝜸 , 𝛽 ( and 𝜷 with the algorithm 1 . Confidence intervals of 𝛽 ( and 𝜷 are estimated from 500 bootstrap samples after having identified 𝜸 . To replicate the simulation study in 1 we only kept components as long as the average DfD value was below 2. To deal with differences in the order of the identified components, we reordered the vectors according to best match with the truth. See Supplementary Tables 4-5 for the true and estimated parameters as well as statistics, and see 1 for details on the data simulation.

## Details on model fitting

To identify 𝜸, 𝛽 ( and 𝜷 in FUNCOIN, the model is first fitted by maximizing the likelihood function (i.e. minimizing the negative log-likelihood, ignoring constants) using a block coordinate decent algorithm 1 . The Python package (FUNCOIN, https://github.com/kobbersmed/funcoin) allows for setting the number of initial conditions to try, the maximal number of iterations, and tolerance. The fitting procedure is repeated with the specified number of random or prespecified initial conditions. Each run terminates if the maximal number of iterations is reached or if no coordinate in the 𝜸, 𝛽 ( , and 𝜷 being optimized changes more than the specified tolerance. From all runs with different initial conditions, the best fit is kept. If the unbiased version is selected (which is default), the identification of a component concludes with determining 𝛽 ( and 𝜷 as described above to resolve bias on the model coefficients. All model fitting in our study used the unbiased version, with default values of number of initial conditions (20), max iterations (1000), and tolerance (1e-4).

## Brain maps

The rsfMRI data used comes with the UKB ICA-parcellation with a 55 network parcellation. These components are provided in MNI152 space, 2 mm resolution, as a 4D array of shape 91x109x91x55 in the NIFTI image format. Brain maps shown in Figure 2 show the combination of ICA components for the identified 𝜸 𝟏 and 𝜸 𝟐 . These are obtained by right-multiplying the ICA array with 𝜸 𝟏 and 𝜸 𝟐 respectively, which are of shape 55x1. The brain maps were created using fsleyes version 1.12.6 from the FMRIB software library (FSL) (https://zenodo.org/records/11047709). Labelling of the brain areas and networks in the identified components was done by visual inspection and verified by the Neurosynth decoder (https://neurosynth.org/decode/) 14 .

## Z -score calculation

For each subject, i , the Z -score of component j is determined as

<!-- formula-not-decoded -->

where 𝑢 .,$ = logc𝜸 . 4 𝛴 $ 𝜸 . e is subject i 's value from component j , 𝑢 a .,$ is the model prediction for subject i , and 𝜎 . is the SD in component j . The same formula was used in the edgewise models.

## Model formulation of the edgewise linear model

In the edgewise model in Figure 4 and 5, we vectorized the 1485 unique elements of the correlation matrix, S i , for each subject i, and applied Fisher's z-transformation on each correlation value. Fisher's z-transformation is a standard way of making the correlation values closer to normally distributed.

## Implementation of statistical tests

Fisher's exact and Mann-Whitney U tests were conducted with the scipy.stats python module (v1.13.1). The EVDs in Figure 5 and Supplementary Fig. 4 were fitted using scipy.stats.genextreme (v1.13.1). Spearman correlations (Figs. 5E-F) were calculated with scipy.stats,spearmanr (v1.13.1). Benjamini-Yekutieli was carried out with the scikit-learn implementation (v1.5.1).

## Supplementary figures

Supplementary Figure 1: Bias correction

![Image](./media-1_artifacts/image_000000_147ad738fc4f4213c9f6649a4e9a29308a6d0c4fbfffe43209c88fcaf831143a.png)

Comparing model predictions of the original covariate-assisted principal regression algorithm and FUNCOIN to the average values in one-year age groups. This illustrates the bias of the original algorithm and that the extra step of linear regression attenuates this bias .

Supplementary Figure 2: Visualization of the two projections identified on which the normative model is based (Fig. 2). The projections weight the 55 ICA components (y-axis) by the loadings identified (x-axis).

![Image](./media-1_artifacts/image_000001_a8bfaab391be76e23b589725c3512284d560a0c229b2cef2b8bf062e86cc3ea1.png)

## Edgewise model, two best edges

![Image](./media-1_artifacts/image_000002_ec22e35667e34dad2c497cca4652c63de0b846dda55db5e99af183df30bad921.png)

## Supplementary Figure 3: Model validation of the two best models in the edgewise approach

A . QQ-plots from the training data in the two edges with highest R 2 in the element-wise model. B-C. Residuals vs. fitted value for the training data (D) and test data (E) (1000 random points for each sex and each projection are visualized) show variance homogeneity of the residuals, which is a necessary assumption for linear regression and the Z -score interpretation . D. Out-of-sample Z -scores from the two edges show good agreement with a standard normal distribution.

![Image](./media-1_artifacts/image_000003_5b11dc69a65419bd92168f67f3e90473b3ca588734f06b12c0606eba8bbdb073.png)

Supplementary Figure 4: Supplementary tests of methods for summarizing

Z

-scores

A-B. Same analyses as in Fig. 4A-B, but restricted to PD subjects scanned before the time of PD diagnosis. Like in Fig. 4B, a group difference is found in the distribution of counts of outliers (z&lt;-2) but no significant difference was seen in the subject level analysis. C-D. Same analyses as in Fig. 4A-B and Supplementary Fig. 4A-B, but counting the number of outliers as |z|&lt;2 (instead of separating the two directions of deviation). Again, group-level differences are found but there was no significant difference between group when comparing proportions of outlier subjects. E. Same analysis as Fig. 4D, but restricted to PD subjects scanned before the time of PD diagnosis. Like in Fig. 4D, no

significant difference was found. F-H. Same analysis as in Fig. 4C-D and Supplementary Fig. 4E, but defining outliers as |z|&gt;2 (instead of separating the two directions of deviation). Like in Fig. 4D and Supplementary Fig. 4E, no significant difference was found.

Supplementary Figure 5: Model validation and sanity checks

![Image](./media-1_artifacts/image_000004_7465b5acd77f57ab06998bdda63e0c2056b6fee53c663ceb7228d2a085f91dd1.png)

A. QQ-plots from the test data give us no reason to suspect non-normality, which is assumed in the Z -score interpretation. B. Residuals vs. fitted value test data (250 random points for each sex and each projection are visualized) show variance homogeneity of the out-of-sample residuals, which is a necessary assumption the Z -score interpretation .

![Image](./media-1_artifacts/image_000005_c97d0c46c273eddd0aaa0673dce9e53536bde432753bbc4a90b9604bfce78339.png)

![Image](./media-1_artifacts/image_000006_f790e907cdcde80624171b822dbdefed4fa68b4a0c4c642d0d5a6ef14c225b67.png)

## Supplementary Figure 6: Distribution of Z -scores by scanning site

A . Boxplots of Z 1 and Z 2 values distributed according to scanning site. Boxes depict range between 1 st and 3 rd quartile, orange line indicates the median, whiskers show minimum and maximum values within 1.5 x the interquartile range from the box. Circles show data points further away from the box than 1.5 x interquartile range. Z -scores distribute similarly at the different sites. The different amounts of outliers at different sites are proportional to the number of subjects scanned. B. Same as in A, but for out-of-sample subjects.

![Image](./media-1_artifacts/image_000007_e430189adf7eb10ec6cb2eb5294d0d3deae0e0130a5bb7d582901b50060e7cb3.png)

## Supplementary Figure 7: Assessing computation time

A. Computation time for identifying 2 projections with varying number of random healthy subjects, 55 ICA components. Plot shows mean of 50 repetitions and error bars indicate standard deviation. B. Computation time for identifying 2 projections with varying number of ICA components, 5000 subjects. Plot shows mean of 50 repetitions and error bars indicate standard deviation.

## Supplementary Table 1

|                   | Coeff.   | Variable   | Estimate (SE)   | 95%-CI           |
|-------------------|----------|------------|-----------------|------------------|
| First projection  | b 1,0    | Intercept  | -1.085 (0.010)  | [-1.101; -1.066] |
| First projection  | b 1,1    | Sex        | 0.6129 (0.014)  | [0.585; 0.641]   |
| First projection  | b 1,2    | Age        | 0.4755 (0.018)  | [0.440; 0.511]   |
| First projection  | b 1,3    | Sex-age    | -0.0935 (0.026) | [-0.145; -0.043] |
| Second projection | b 2,0    | Intercept  | -0.5402 (0.011) | [-0.562; -0.518] |
| Second projection | b 2,1    | Sex        | -0.5080 (0.017) | [-0.541; -0.475] |
| Second projection | b 2,2    | Age        | -0.0412 (0.021) | [-0.083; -0.001] |
| Second projection | b 2,3    | Sex-age    | -0.1261 (0.031) | [-0.186; -0.066] |

## Supplementary Table 1 : Main model coefficients (FUNCOIN)

b 0 and b coefficients in our main model as identified with FUNCOIN. Standard error (SE) and 95-% confidence intervals (95%-CI) are estimated with standard methods from the linear regression carried out at the end of the FUNCOIN algorithm (see Methods). All coefficients but b 2,2 are significantly different from 0, as seen from the confidence intervals, but significance should be interpreted with caution, since meaningless small contributions can become significant with very large N. Note that the age variable is linearly transformed to the interval [0,1], where 0 and 1 are the minimum and maximum age in the whole dataset (44.56 and 85.43 years resp.). Sex was coded as a categorical variable with 0 denoting female, 1 denoting male.

## Supplementary Table 2

| Comparing best fit coefficients found in the 'basic' and 'site' models   | Comparing best fit coefficients found in the 'basic' and 'site' models   | Comparing best fit coefficients found in the 'basic' and 'site' models   | Comparing best fit coefficients found in the 'basic' and 'site' models   | Comparing best fit coefficients found in the 'basic' and 'site' models   | Comparing best fit coefficients found in the 'basic' and 'site' models   | Comparing best fit coefficients found in the 'basic' and 'site' models   | Comparing best fit coefficients found in the 'basic' and 'site' models   | Comparing best fit coefficients found in the 'basic' and 'site' models   | Comparing best fit coefficients found in the 'basic' and 'site' models   | Comparing best fit coefficients found in the 'basic' and 'site' models   |
|--------------------------------------------------------------------------|--------------------------------------------------------------------------|--------------------------------------------------------------------------|--------------------------------------------------------------------------|--------------------------------------------------------------------------|--------------------------------------------------------------------------|--------------------------------------------------------------------------|--------------------------------------------------------------------------|--------------------------------------------------------------------------|--------------------------------------------------------------------------|--------------------------------------------------------------------------|
|                                                                          | Proj. 1                                                                  | Proj. 1                                                                  | Proj. 2                                                                  | Proj. 2                                                                  | Proj. 3                                                                  | Proj. 3                                                                  | Proj. 4                                                                  | Proj. 4                                                                  | Proj. 5                                                                  | Proj. 5                                                                  |
| Model                                                                    | Basic                                                                    | Site                                                                     | Basic                                                                    | Site                                                                     | Basic                                                                    | Site                                                                     | Basic                                                                    | Site                                                                     | Basic                                                                    | Site                                                                     |
| b 0  (intercept)                                                         | -1.085                                                                   | -1.080                                                                   | -0.540                                                                   | -0.551                                                                   | -0.874                                                                   | -0.565                                                                   | -0.240                                                                   | -0.766                                                                   | -0.667                                                                   | -0.773                                                                   |
| b 1  (sex)                                                               | 0.613                                                                    | 0.604                                                                    | -0.508                                                                   | -0.507                                                                   | -0.364                                                                   | 0.404                                                                    | 0.355                                                                    | -0.339                                                                   | -0.331                                                                   | 0.292                                                                    |
| b 2  (age)                                                               | 0.475                                                                    | 0.504                                                                    | -0.041                                                                   | -0.053                                                                   | 0.158                                                                    | -0.403                                                                   | -0.425                                                                   | 0.042                                                                    | 0.163                                                                    | 0.405                                                                    |
| b 3  (site 2)                                                            | -------                                                                  | -0.063                                                                   | -------                                                                  | 0.046                                                                    | -------                                                                  | 0.060                                                                    | -------                                                                  | 0.038                                                                    | -------                                                                  | -0.099                                                                   |
| b 4  (site 3)                                                            | -------                                                                  | -0.058                                                                   | -------                                                                  | 0.014                                                                    | -------                                                                  | 0.153                                                                    | -------                                                                  | 0.035                                                                    | -------                                                                  | -0.093                                                                   |
| b 5  (site 4)                                                            | -------                                                                  | -0.058                                                                   | -------                                                                  | 0.008                                                                    | -------                                                                  | 0.060                                                                    | -------                                                                  | 0.026                                                                    | -------                                                                  | -0.056                                                                   |
| b 6  (sex-age)                                                           | -0.094                                                                   | -0.085                                                                   | -0.126                                                                   | -0.125                                                                   | -0.136                                                                   | -0.029                                                                   | -0.006                                                                   | -0.124                                                                   | -0.024                                                                   | -0.030                                                                   |

## Supplementary Table 2 : Comparing coefficients in the 'basic' and 'site' models

Coefficients obtained by fitting the 'basic model' presented in Results (covariates sex, age, sex-age interaction) and a 'site effect model' (covariates sex, age, sex-age interaction, scanning site). The two first projections identified have close to identical model coefficients ( b 0 , b , green shading) and projections ( g 1 , g 2 , see Supplementary Table 3). The third identified projection in the scanning site model ( g s3 ) captures some site effect (orange shading), making the two models disagree on the third component. This causes discrepancy in the third and subsequent identified components in the two models, since they are identified after removing component 3 in each model. Sex and age were coded as explained in Methods. Site was a dummy coded, categorical variable.

## Supplementary Table 3

| Comparing identified gamma vectors in the 'basic' and 'site' models   | Comparing identified gamma vectors in the 'basic' and 'site' models   | Comparing identified gamma vectors in the 'basic' and 'site' models   | Comparing identified gamma vectors in the 'basic' and 'site' models   | Comparing identified gamma vectors in the 'basic' and 'site' models   |
|-----------------------------------------------------------------------|-----------------------------------------------------------------------|-----------------------------------------------------------------------|-----------------------------------------------------------------------|-----------------------------------------------------------------------|
|                                                                       | g b1                                                                  | g b2                                                                  | g b4                                                                  | g b5                                                                  |
| g s1                                                                  | g s1· g b1 =0.999                                                     | g s1· g b2 =-0.012                                                    | g s1· g b4 =-0.023                                                    | g s1· g b5 =0.005                                                     |
| g s2                                                                  | g s2· g b1 =0.012                                                     | g s2· g b2 =1.000                                                     | g s2· g b4 =-0.007                                                    | g s2· g b5 =0.002                                                     |
| g s3                                                                  | g s3· g b1 =0.039                                                     | g s3· g b2  =0.014                                                    | g s3· g b4 =0.827                                                     | g s3· g b5 =-0.102                                                    |
| g s4                                                                  | g s4· g b1 =0.003                                                     | g s4· g b2 =0.012                                                     | g s4· g b4 =-0.307                                                    | g s4· g b5 =0.331                                                     |
| g s5                                                                  | g s5· g b1 =-0.012                                                    | g s5· g b2 =0.007                                                     | g s5· g b4 =0.284                                                     | g s5· g b5 =0.343                                                     |

## Supplementary Table 3 : Comparing projections in the 'basic' and 'site' models

Comparing g projection identified in the 'basic' version of FUNCOIN presented in results (covariates sex, age, and sex-age interaction) to a 'site' model that in addition includes scanning site as a covariate (covariates sex, age, sexage interaction, scanning site). Identified g vectors have norm 1 and are compared between models by taking the dot product. The g s are sign invariant, making the absolute value of the dot product the indication of similarity with a value of 1 indicating perfect alignment. The two models identify the same g 1 and g 2 (green shading). This is compatible with the scanning site coefficients from the scanning site model being close to 0 (Supplementary Table 2) and the other coefficients being close to equal. Note that FUNCOIN identifies orthogonal components, and the first two components from each model are therefore orthogonal to all other (gray shading). g s3 captures some effect of scanning site (see Supplementary Table 2) and is therefore different from g b3 . Subsequent projections ( g b4, g b5, g s4, g s5 ) are dissimilar (orange and red shading), because they are identified after removing the third component in each model.

## Supplementary Table 4:

|                   | b     |   Truth | Estimate (SE)   |    CP |
|-------------------|-------|---------|-----------------|-------|
| First projection  | b 1,0 |       4 | 3.884 (0.099)   | 0.27  |
|                   | b 1,1 |      -1 | -1.001 (0.028)  | 0.95  |
| Second projection | b 2,0 |       1 | 1.281 (0.888)   | 0.685 |
|                   | b 2,1 |       1 | 0.779 (0.635)   | 0.835 |

## Supplementary Table 4 : Simulation study coefficients

Simulation study results from the covariate-assisted principal regression model: Estimated beta mean, standard error (SE), and coverage probability of confidence intervals (CP) in a simulation study reproduced from 1 . Average and SE are estimated from 200 simulations. Confidence intervals and coverage probabilities are obtained by bootstrapping with 500 samples for each simulation.

## Supplementary Table 5

|                   | g     |   Truth | Estimate (SE)   |
|-------------------|-------|---------|-----------------|
| First projection  | g 1,1 |   0.447 | 0.428 (0.147)   |
| First projection  | g 1,2 |  -0.862 | -0.817 (0.059)  |
| First projection  | g 1,3 |   0.138 | 0.131 (0.050)   |
| First projection  | g 1,4 |   0.138 | 0.139 (0.149)   |
| First projection  | g 1,5 |   0.138 | 0.116 (0.221)   |
| Second projection | g 2,1 |   0.44  | 0.445 (0.088)   |
| Second projection | g 2,2 |   0.138 | 0.038 (0.290)   |
| Second projection | g 2,3 |  -0.862 | -0.745 (0.304)  |
| Second projection | g 2,4 |   0.138 | 0.130 (0.075)   |
| Second projection | g 2,5 |   0.138 | 0.132 (0.149)   |

## Supplementary Table 5 : Simulation study projections

Identified gamma projections in the simulation study from the covariate-assisted principal regression model. Estimated gamma mean and standard error (SE) in a simulation study reproduced from 1 . Average and SE are estimated from 200 simulations.

## Supplementary Table 6

|                                      | Test sta's'c         | Unadjusted  p -values   | FDR-corrected  p -values   |
|--------------------------------------|----------------------|-------------------------|----------------------------|
| Figure 3A                            |                      |                         |                            |
| BD: Z 1 <-2; Z 1 >2; Z 2 <-2; Z 2 >2 | (permuta*on tes*ng)  | 1e-4; 0,98; 0.75, 0.16  | 0.002; 1; 1; 0.81          |
| PD: Z 1 <-2; Z 1 >2; Z 2 <-2; Z 2 >2 | (permuta*on tes*ng)  | 0.18; 0.01; 0.004; 0.89 | 0.81; 0.09; 0.046; 1       |
| Figure 3B                            |                      |                         |                            |
| PD: Z 1 <-2; Z 1 >2; Z 2 <-2; Z 2 >2 | (permuta*on tes*ng)  | 0.001; 1; 0.67; 0.34    | 0.01; 1; 1; 1              |
| Figure 4B                            |                      |                         |                            |
| BD: Z 1 <-2; Z 1 >2; Z 2 <-2; Z 2 >2 | (permuta*on tes*ng)  | 0.21; 0.12; 0.18; 0.04  | 0.89; 0.89; 0.89; 0.82     |
| PD: Z 1 <-2; Z 1 >2; Z 2 <-2; Z 2 >2 | (permuta*on tes*ng)  | 0.19; 0.84; 0.94; 0.62  | 0.89; 1; 1; 1              |
| Figure 4C                            |                      |                         |                            |
| PD: Z 1 <-2; Z 1 >2; Z 2 <-2; Z 2 >2 | (permuta*on tes*ng)  | 0.12; 1; 0.69; 0.38     | 1; 1; 1; 1                 |
| Figure 5A                            |                      |                         |                            |
| BD vs. healthy: Z<-2, Z>2            | U=644117.5; U=659432 | 0.12; 0.24              | 0.39; 0.51                 |
| PD vs. healthy: Z<-2, Z>2            | U=694754.5; U=854953 | <1e-6; 0.14             | <1e-5; 0.39                |
| Figure 5B                            |                      |                         |                            |
| BD: # (Z<-2); # (Z>2)                | (permuta*on tes*ng)  | 0.89; 1                 | 1; 1                       |
| PD: # (Z<-2); # (Z>2)                | (permuta*on tes*ng)  | 0.34; 0.79              | 1; 1                       |
| Figure 5D                            |                      |                         |                            |
| BD: mean 1% (Z<-2); mean 1% (Z>2)    | (permuta*on tes*ng)  | 0.67; 0.37              | 1; 1                       |
| PD: mean 1% (Z<-2); mean 1% (Z>2)    | (permuta*on tes*ng)  | 0.35; 0.94              | 1; 1                       |

## Supplementary Table 6 : Statistics and p-values

Test statistics and p-values for the results presented in the main figures. See Materials and methods. for details.

## References

1. Zhao Y, Wang B, Mostofsky SH, Caffo BS, Luo X. Covariate Assisted Principal regression for covariance matrix outcomes. Biostatistics . Jul 17 2021;22(3):629-645. doi:10.1093/biostatistics/kxz057
2. Flury BN, Gautschi W. An Algorithm for Simultaneous Orthogonal Transformation of Several Positive Definite Symmetric Matrices to Nearly Diagonal Form. SIAM Journal on Scientific and Statistical Computing . 1986;7(1):169-184. doi:10.1137/0907013
3. Flury BN. Common Principal Components in k Groups. Journal of the American Statistical Association . 1984/12/01 1984;79(388):892-898. doi:10.1080/01621459.1984.10477108
4. Flury BN. Asymptotic Theory for Common Principal Component Analysis. The Annals of Statistics . 1986;14(2):418-430.
5. Zhao Y, Caffo B, Luo X, Alzheimer's Disease Neuroimaging I. Principal regression for high dimensional covariance matrices. Electron J Stat . 2021;15(2):4192-4235. doi:10.1214/21ejs1887
6. Ledoit O, Wolf M. A well-conditioned estimator for large-dimensional covariance matrices. Journal of Multivariate Analysis . 2004/02/01/ 2004;88(2):365-411. doi:https://doi.org/10.1016/S0047-259X(03)00096-4
7. He Y, Zou C, Zhao Y. Covariance Regression with High-Dimensional Predictors. 2024:arXiv:2404.06701. doi:10.48550/arXiv.2404.06701 Accessed April 01, 2024.

## https://ui.adsabs.harvard.edu/abs/2024arXiv240406701H

8. Zhao Y, Caffo BS, Luo X. Longitudinal regression of covariance matrix outcomes. Biostatistics . Apr 15 2024;25(2):385-401. doi:10.1093/biostatistics/kxac045
9. Zhao Y, Zhao Y. Covariance-on-Covariance Regression. 2022:arXiv:2212.09866. doi:10.48550/arXiv.2212.09866 Accessed December 01, 2022. https://ui.adsabs.harvard.edu/abs/2022arXiv221209866Z
10. Zhao Y, Caffo BS, Wang B, Li CR, Luo X. A whole-brain modeling approach to identify individual and group variations in functional connectivity. Brain Behav . Jan 2021;11(1):e01942. doi:10.1002/brb3.1942
11. Fraza CJ, Dinga R, Beckmann CF, Marquand AF. Warped Bayesian Linear Regression for Normative Modelling of Big Data. 2021;doi:10.1101/2021.04.05.438429
12. Kia SM, Huijsdens H, Rutherford S, et al. Closing the life-cycle of normative modeling using federated hierarchical Bayesian regression. PLoS One . 2022;17(12):e0278776. doi:10.1371/journal.pone.0278776
13. de Boer AAA, Bayer JMM, Kia SM, et al. Non-Gaussian normative modelling with hierarchical Bayesian regression. Imaging Neuroscience . 2024;2:1-36. doi:10.1162/imag\_a\_00132
