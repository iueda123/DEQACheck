1234567890():,;

1234567890():,;

![Image](Rutherford2022b_artifacts/image_000000_554d1bbae2d841d6dd88fa029436f15a6a18726bbc0c418135531fb9cf83b3f8.png)

![Image](Rutherford2022b_artifacts/image_000001_d6459d6262b319688cc2fa6b8e5ab3ff9f0e779255cda4f0a990169da16d5a6f.png)

![Image](Rutherford2022b_artifacts/image_000002_a574615148297a725ac96429dfaff513c73d42eebe2281c9b9729e2f61189fb4.png)

## The normative modeling framework for computational psychiatry

Saige Rutherford 1,2,3 ✉ , Seyed Mostafa Kia 1,2,4 , Thomas Wolfers 5,6 , Charlotte Fraza 1,2 , Mariam Zabihi 1,2 , Richard Dinga 1,2 , Pierre Berthet 5,6 , Amanda Worker 7 , Serena Verdi 8,9 , Henricus G. Ruhe 1,10,12 , Christian F. Beckmann 1,2,11,12 and Andre F. Marquand 1,2,7,12

Normative modeling is an emerging and innovative framework for mapping individual differences at the level of a single subject or observation in relation to a reference model. It involves charting centiles of variation across a population in terms of mappings between biology and behavior, which can then be used to make statistical inferences at the level of the individual. The /uniFB01 elds of computational psychiatry and clinical neuroscience have been slow to transition away from patient versus ' healthy ' control analytic approaches, probably owing to a lack of tools designed to properly model biological heterogeneity of mental disorders. Normative modeling provides a solution to address this issue and moves analysis away from case -control comparisons that rely on potentially noisy clinical labels. Here we de /uniFB01 ne a standardized protocol to guide users through, from start to /uniFB01 nish, normative modeling analysis using the Predictive Clinical Neuroscience toolkit (PCNtoolkit). We describe the input data selection process, provide intuition behind the various modeling choices and conclude by demonstrating several examples of downstream analyses that the normative model may facilitate, such as strati /uniFB01 cation of high-risk individuals, subtyping and behavioral predictive modeling. The protocol takes ~1 -3 h to complete.

## Introduction

Clinical neuroscientists have recently acknowledged two realities that have disrupted the way research is conducted: /uniFB01 rst, that to understand individual differences it is necessary to move away from group average statistics 1 -7 and, second, that the classical diagnostic labels of psychiatric disorders are not clearly represented in the underlying biology 8 -11 . Initiatives such as RDoC 9,12,13 , HiTOP 10,14,15 and ROAMER 16,17 were established in response and seek to re /uniFB01 ne the nosology of mental disorders by mapping biobehavioral dimensions that cut across heterogeneous disorder categories. Despite this awareness and an increasing interest in quantifying individual differences, the /uniFB01 eld has still been slow to transition away from case -control comparisons that aim to contrast patient groups with healthy control groups and assume that clinical groups are distinct and homogeneous. A key barrier that has impeded progress is a lack of alternative analysis methods, designed to model variation across individuals, also known as heterogeneity 18 . Nearly all existing techniques for connecting the brain to behavior operate at the group level and provide no path to individual-level inference 19 -21 . Normative modeling is a framework for understanding differences at the level of a single subject or observation while mapping these differences in relation to a reference model (Fig. 1). It involves charting centiles of variation across a population in terms of mappings between biology and behavior, which can then be used to make statistical inferences at the level of the individual, akin to the use of growth charts in pediatric medicine (Fig. 1a). The practice of normative modeling in clinical neuroscience was developed to provide additional information beyond what can be learned from case -control modeling approaches (see ' Development of the protocol ' below for further information). Case -control thinking assumes that the mean is representative of the population, when it may not be (e.g., if the clinical

1 Donders Institute for Brain, Cognition, and Behavior, Radboud University, Nijmegen, the Netherlands. 2 Department of Cognitive Neuroscience, Radboud University Medical Center, Nijmegen, the Netherlands. 3 Department of Psychiatry, University of Michigan, Ann Arbor, MI, USA. 4 Department of Psychiatry, Utrecht University Medical Center, Utrecht, the Netherlands. 5 Department of Psychology, University of Oslo, Oslo, Norway. 6 Norwegian Center for Mental Disorders Research, University of Oslo, Oslo, Norway. 7 Department of Psychological Medicine, Institute of Psychiatry, Psychology and Neuroscience, King ' s College London, London, UK. 8 Centre for Medical Image Computing, Medical Physics and Biomedical Engineering, University College London, London, UK. 9 Dementia Research Centre, UCL Queen Square Institute of Neurology, London, UK. 10 Department of Psychiatry, Radboud University Medical Center, Nijmegen, the Netherlands. 11 Centre for Functional MRI of the Brain, University of Oxford, Oxford, UK. 12 These authors contributed equally to this work: Henricus G. Ruhe, Christian F. Beckmann, Andre F. Marquand. ✉ e-mail: saige.rutherford@donders.ru.nl

## PROTOCOL

Fig. 1 | Conceptual overview of normative modeling. a , Classical example of normative modeling: the use of height and weight growth charting in pediatrics. b , Left: case -control models theoretically make assumptions that there is a boundary that can separate groups and that there is withingroup homogeneity. Right: in reality, there is nested variation across controls and patient groups and within-group heterogeneity, resulting in unclear separation boundaries. Normative modeling is well equipped to handle this reality. c , An example application of normative modeling in computational psychiatry using neuroimaging data. Mean cortical thickness ( y axis) is predicted from age ( x axis) using a training set consisting of multisite structural MRI from neurotypical controls and a test set consisting of neurotypical controls and patient groups. Every dot indicates the deviation score for a single individual from normal development. d , Regression model equation and design matrix setup for the model shown in c .

![Image](Rutherford2022b_artifacts/image_000003_3ffa53c3c74c91d4d083d26988c21840819d69ca2de072f8788a2284c77e28b5.png)

population is diffuse or comprises multiple subpopulations). Therefore, normative modeling has become a leading tool for precision medicine research programs and has been used in many clinical contexts 22 (see ' Applications and comparison with other methods ' below for more examples).

Neuroscience has historically brought together scientists from diverse educations, for example, some from a clinical background and others having a mathematics background. The interdisciplinary nature introduces a challenge in bridging the gap between technical and clinical perspectives. This is a key challenge that aligns with the aims of the open-science movement and brain-hack community 23 , in other words, to distill the essential components of the analytic work /uniFB02 ow into a consistent and widely applicable protocol. This helps to avoid ' research debt ' , i.e., a lack of ideas being digested 24 . This distiller mindset is crucial for confronting research debt and embracing paradigm shifts in thinking, such as moving from case -control comparisons to the normative modeling framework.

The purpose of this work is to distill the methods of normative modeling, an advanced analysis technique, into an actionable protocol that addresses these challenges in that it is accessible to researchers within the diverse /uniFB01 eld of clinical neuroscience. We distill the essential components of a normative modeling analysis and provide a demonstrative analysis from start to /uniFB01 nish using the Predictive Clinical Neuroscience toolkit (PCNtoolkit) software (https://pcntoolkit.readthedocs.io/en/ latest). We describe the input data selection process, give an overview of the various modeling choices and conclude by demonstrating several examples of downstream analyses that the normative model may facilitate, such as strati /uniFB01 cation of high-risk individuals, subtyping and behavioral predictive modeling.

## Development of the protocol

Normative modeling has a long history that relates to statistics and measurement theory and has many applications from medicine to economics to neuroscience. Familiar use cases of normative modeling include growth charting in pediatrics, neurocognitive tests and interpreting graduate school test score percentiles (i.e., scoring 90th percentile on the MCAT). The mathematical and computational development of normative modeling has been /uniFB01 ne-tuned 25 -28 and currently exists as an opensource software Python package, the PCNtoolkit, which we focus on in this manuscript. This toolkit implements many commonly used algorithms for normative modeling and supports multiple industry standard data formats (e.g., NIFTI, CIFTI, text formats). Extensive documentation has been written to accompany this protocol and is available online through ' read the docs ' (https://pcntoolkit. readthedocs.io/en/latest). This includes tutorials with sample data for all algorithm implementations, a glossary to help new users understand the jargon associated with the software, and a frequentlyasked-questions page. An online forum (https://gitter.im/PCNtoolkit/community) for communicating questions, bugs, feature requests, etc. to the core team of PCNtoolkit developers is also available. We have developed these open-source resources to promote and encourage individual differences research in computational psychiatry using normative modeling.

## Applications and comparison with other methods

Normative modeling has been applied to many research questions in computational psychiatry and other /uniFB01 elds, including in autism spectrum disorder 29 -31 , attention de /uniFB01 cit hyperactive disorder 32,33 , Alzheimer ' s disease 34 , bipolar disorder and schizophrenia 35 -37 . Crucially, these applications have shown that normative modeling can detect individual differences both in the presence of strong case -control differences (observed in schizophrenia) 36 and in their absence (observed in autism spectrum disorder) 30 . This highlights the value and complementary nature of understanding individual variation relative to group means. These applications have primarily focused on predicting regional structural or functional neuroimaging data (i.e., biological response variables) from phenotypic variables (i.e., clinically relevant covariates) such as age and sex. Age creates a natural, timevarying dimension for mapping normative trajectories and is well suited to applications in which deviations of an individual manifest from a typical trajectory of brain development or aging. However, other phenotypes that have been used in neuroimaging predictive modeling studies such as general cognitive ability 38,39 , social cognition or sustained attention 40,41 are also attractive possibilities to use as covariates, thereby de /uniFB01 ning axes for observing deviation patterns. Normative modeling has also been used to learn mappings between reward sensitivity and reward-related brain activity 42 .

It is important to emphasize that normative modeling is a general regression framework for mapping sources of heterogeneity, refocusing attention on individual predictions rather than group means (e.g., diagnostic labels), and detecting individuals who deviate from the norm. Therefore, it is not limited to a speci /uniFB01 c algorithm or mathematical model, although we recommend certain algorithms based on the research question and available input data. The algorithms in the PCNtoolkit tend to favor Bayesian over frequentist statistics, as there are certain features of Bayesian approaches that facilitate better normative modeling estimation. For example, having a posterior distribution over the parameters helps to better separate different sources of uncertainty, e.g., separating variation ( ' aleatoric uncertainty '-cannot be reduced by adding more data) from modeling (or ' epistemic ' ) uncertainty, which can be reduced by adding more data. These different use cases of normative modeling (algorithm selection, predicting brain from behavior or behavior from the brain) are explained in depth in the ' Experimental design ' section, below.

There is a long history of using regression methods to learn mappings between brain and behavior 43,44 . Principal component regression 20,45,46 , connectome predictive modeling (CPM) 19,47 and canonical correlation analysis (CCA) 48,49 have become mainstream methods for linking brain and behavior. These methods have demonstrated the feasibility of brain-behavior mapping and laid the foundation for individual-differences research to thrive. While these approaches have generated much curiosity and excitement, they are limited in their ability to provide inference at the level of the individual, providing only point estimates (i.e., without associated centiles of variation). Most papers using these tools only report the mean predictive model performance, collapsing information across hundreds or thousands of people into a single number (e.g., model accuracy or regression performance) 46,47,50,51 . The normative modeling framework takes these ideas a step further to quantify and describe how individuals differ statistically, with respect to an expected pattern. In this way, normative modeling breaks the symmetry inherent in the case -control paradigm. In more detail,

principal component regression and CPM differ from normative modeling in terms of how the prediction model is formulated. Principal component regression and CPM set up the regression model such that Y , an n\_subjects × 1 vector (i.e., age or /uniFB02 uid intelligence), is predicted from X , a matrix with n\_subjects × n\_brain dimensions, where n\_brain is typically a reduced feature space selected via a regularization step. This setup makes interpretating which brain features are related to the behavior very challenging. Studies using principal component regression or CPM attempt to interpret the brain feature weights, but as these methods typically use functional MRI (fMRI) connectomic data, consisting of connections and nodes, interpretation often yields a complex wholebrain visualization that is not very informative. The individual-level output of these models is a single point estimate, a predicted behavior score for each subject. These individual point estimates are then summarized by correlating the predicted and true behavior scores, reporting explained variance ( R 2 ) and calculating accuracy (mean squared error). Compared with principal component regression and CPM, normative modeling inverts the regression setup to predict brain region Y , a n\_subjects × 1 vector from X , a matrix with n\_subjects × n\_covariates (i.e., age, sex, /uniFB02 uid intelligence, site, data quality metric). There is a separate regression model for each brain region. The individual-level outputs of normative modeling are the predicted brain score, the predictive variance (separated into modeling and noise components) and a deviation score ( Z -score, how much each subject deviate from the normative range). The overall performance is evaluated by correlating predicted and true values and calculating explained variance, standardized mean squared error and mean standardized log loss. In contrast, CCA estimates a doubly multivariate relationship in that both X and Y are matrices ( X is an n\_subjects × n\_brain matrix and Y is n\_subjects × n\_behavior). While CCA is well suited to detecting that a mapping exists, this still leads to dif /uniFB01 cult interpretation of feature importance, and moreover, CCA is highly prone to over /uniFB01 tting and requires careful assessment of out-ofsample metrics with respect to an appropriate null distribution, which is not always done in practice. Like principal component regression and CPM, CCA also does not provide individual measure of uncertainty or deviation scores.

Case -control inference (e.g., mass univariate group t -testing and classi /uniFB01 cation of patient versus control) examples are perhaps the most interesting comparison with the normative modeling framework. Case -control methods typically require there to be a homogeneous within-group spatial signature, and their success relies on obtaining statistical signi /uniFB01 cance ( P &lt; 0.05). We clarify this point with an example of the assumptions of case -control inference. To detect a group difference in amygdala activation between a control group and a group of individuals with post-traumatic stress disorder during a fMRI task, all individuals in the control group need a similar value of amygdala activation and all individuals in the PTSD group need a similar value of amygdala activation. Then, the mean amygdala activation signal of the control group must be statistically different from the mean amygdala activation signal of the PTSD group after stringent multiple comparison testing correction. These assumptions ignore the fact that different biological processes (i.e., some people have increased activation and others decreased) can lead to similar external behavior. Normative models reveal a different side of the data -that the classical diagnostic labels of psychiatric disorders are not clearly represented in the underlying biology, meaning that patient groups are not well de /uniFB01 ned by a unifying neurosignature -and provide clear evidence for the limitations of case -control paradigms. Brain age models are also in the same family as normative models but generally have a narrower focus on interpreting accelerated/decelerated aging 52,53 or improving prediction accuracy 54 . Brain age models only allow for interpreting centiles of variation in terms of age, which is limited and does not have a clear interpretation in terms of biological variation across individuals.

' All models are wrong, but some are useful ' -George E. P. Box.

There is not one ' best ' modeling approach, and many of the methods presented in this section can be complementary in that they investigate different questions. Before embarking on a computational modeling journey, it is always important to ask questions such as: What are the assumptions made by this model? What type of inference do you want to make (group level, individual level)? What aspect of the predictive model is most important (accuracy, quantifying uncertainty, statistical signi /uniFB01 cance)? Allow your research question to guide the answers and model selection.

## Expertise needed to implement the protocol

We aimed to make this protocol user-friendly to the diverse community of neuroscience, including those with a nontechnical background. The fundamental objective of this protocol is to learn how to implement the normative modeling framework via the PCNtoolkit software without being an expert in

statistics and machine learning. You will be given enough knowledge to set up training and test sets, understand what data should be going into the model, interpret results and make inferences based on the results. Prerequisites of this protocol are basic familiarity with the Python programming language and a computer with a stable internet connection. Complete code, example data and extensive documentation accompany this protocol; thus, writing code from scratch is unnecessary. Of course, it is our intention for readers to be inspired by this protocol and to use the normative modeling framework in more ways than presented here. If you wish to use the framework presented in this protocol beyond the provided code, familiarity with the Linux command line, bash scripting, setting up virtual environments and submitting jobs to high-performance clusters would also be helpful.

## Limitations

## Big data require automated QC

As datasets grow to meet the requirements of becoming population-level or big data, there is typically a need to rely on automated quality control (QC) metrics 55 . This means there is potential to unintentionally include poor-quality data, which could, in turn, affect the results. The training and test dataset used in this protocol has been manually quality checked by visualizing every subject ' s raw T1-weighted volume with their corresponding Freesurfer brain mask as an overlay using an online (JavaScript-based) image viewer. Quality-checking code and further instructions for use are made available on GitHub. These images were inspected for obvious quality issues, such as excess /uniFB01 eld-ofview cutoff, motion artifacts or signal dropout. Subjects that were /uniFB02 agged as having quality issues were excluded from the sample. Users should consider manually quality checking their own data if they wish to add additional samples to the dataset.

## Multisite confounds and data availability

Pooling data from multiple sites is often a necessary step to create diverse datasets and reach suf /uniFB01 cient sample sizes for machine learning analyses. When combining data from different studies, several challenges arise. First, there are often different MRI scanners at each site that also have different acquisition parameters. These MRI hardware and software divergences give rise to substantial nuisance variance that must be properly accounted for when modeling the data. Second, there may be sampling differences, for example due to different inclusion criteria and de /uniFB01 nitions of diagnostic labels at each site. For example, if one site uses the Structured Clinical Interview for DSM-5 (SCID-5) administered by a trained mental health professional who is familiar with the DSM-5 diagnostic criteria, while another site relies on self-report questionnaire data to de /uniFB01 ne clinical labels. This increases the heterogeneity within the clinical groups (e.g., by mixing inclusion criteria across cohorts) and could also add noise to the diagnostic labels (e.g., if diagnostic assessments have different reliability across studies). This is important to consider if clinical labels are used to separate data into training and testing sets (i.e., controls only in the training set) and when comparing the outputs of normative modeling across patient groups from different sites.

Furthermore, there are likely to be dissimilarities in the available demographic, cognitive and clinical questionnaire data across sites as well, which needs to be considered when deciding which studies to include and which covariates to use in modeling. If the goal is to share the model, allowing transfer to new samples, using unique covariates that are speci /uniFB01 c to your sample (and not commonly collected) will hinder the ability of others to use your model on their own data. There is a careful balance that should be considered regarding the bene /uniFB01 ts gained from a new site joining the sample versus the site-related nuisance variance that accompanies the addition of new sites.

## Experimental design

There are many choices and considerations that should be carefully planned before embarking on a normative modeling analysis -the decision points can be grouped into the following stages: data selection, data preparation, algorithm and modeling, and evaluation and interpretation. These stages, and the corresponding step numbers of the procedure, are summarized in Fig. 2. There are additional resources and support for running normative modeling analysis that are summarized in Fig. 3.

## Data selection: reference cohort inclusion criteria

Creating the training dataset that will serve as the ' normative ' reference cohort is the /uniFB01 rst important decision. Ideally, the training dataset will be a large and representative sample, and the included subjects should not be missing vital demographic (age, sex) or biological (neuroimaging) data. However, data

## PROTOCOL

## NATURE PROTOCOLS

Left Hemi Precentral (sup part) cortical thickness

Fig. 2 | Practical overview of normative modeling framework. The work /uniFB02 ow consists of four stages: data selection, data preparation, algorithm and modeling, and evaluation and interpretation, which are visualized by the numbered blue-shaded boxes. The steps involved at each of these stages are summarized in the box below and highlighted in the images above. In stage 3: GPR, Gaussian process regression; BLR, Bayesian linear regression; HBR, hierarchical Bayesian linear regression. In stage 4: PCA, principal component analysis; ICA, independent component analysis; NMF, non-negative matrix factorization.

![Image](Rutherford2022b_artifacts/image_000004_ecb4deb1e4c175a729993c22f348f8e95724d515b6330d32586ebf8371ca59e5.png)

imputation may be used if necessary but should be used cautiously. In most research studies, data are missing not at random, and we interpret more than just mean effects. In this case, mean imputation may bias results and other forms of imputation should be considered 56,57 . It is important that the reference cohort provides good coverage (complementary covariates) of the test set (e.g., clinical) population.

The sample size of the reference cohort (training set) is important to consider in normative modeling, although we emphasize that the focus is different to classical power calculations, which target a fundamentally different question (i.e., determining a required sample size to detect a grouplevel comparison of a speci /uniFB01 ed effect size at a given signi /uniFB01 cance level). In contrast, in normative modeling, the focus is usually on quantifying deviations from a reference model at the individual level. In this context, the size of the reference cohort primarily in /uniFB02 uences the test set deviation scores by in /uniFB02 uencing the accuracy and precision with which the target phenotype (i.e., response variable) can be predicted. As the sample sizes increase, the predictive intervals will shrink, which results in an increased sensitivity to detect individual differences. However, there is no speci /uniFB01 c cutoff that represents an ideal sample size, and we emphasize that context is key. For instance, you could build a clinical normative model for a sample of individuals with major depressive disorder (MDD) for the purpose of strati /uniFB01 cation or detecting subgroups (individuals who have recurrent episodes or individuals who do not respond to medication). In this case, the reference cohort might consist of individuals that have experienced single MDD episodes and those that have responded well to medication. The sample size for this normative modeling research question would likely be relatively small owing to data availability (e.g., clinical datasets typically have stricter data sharing requirements). The main takeaway from this MDD example is that sample size is highly dependent on the research question, which in turn guides the inclusion criteria for the reference cohort you want to measure deviations from. If you are modeling ' healthy ' lifespan populations, the sample size will likely be large (on the order of thousands) because of the plethora of publicly shared data that can be leveraged. On the other hand, if you want to model a speci /uniFB01 c clinical population or a speci /uniFB01 c functional task, the sample size will be smaller owing to availability of data. A smaller dataset that properly addresses the research question at hand is completely acceptable.

## NATURE PROTOCOLS

## a Extensive documentation

## https://pcntoolkit.readthedocs.io/en/latest/

![Image](Rutherford2022b_artifacts/image_000005_f4d7842e0f654c859f4b84fcb8f13b8eb04166a78d83370f4ec3f8fe9a0d545d.png)

- b Documentation for estimate function

estinate(covfile, respfile, [extra\_argunents))

where the variables are defined below. Note that either the cfolds parameter or (testcov, testresp) should be specified, but not both.

Parameters:

- '+ respfile -response variables for the normative model
* covfile -covariates used to predict the response variable
+ maskfile -mask used to apply to the data (nifti only)
+ evfolds -Number of cross-validation folds
+ testcov -Test covariates
+ testresp -Test responses
+ alg -Algorithm for normative model
- '+ configparam -Parameters controlling the estimation algorithm
- '+ saveoutput -Save the output to disk? Otherwise returned as arrays
+ outputsuffix -Text string to add to the output filenames
+ nscale -Scaling approach for input covariates, could be 'None' (Default), 'standardize, 'minmax, or 'robminmax.
- '+ outscale -Scaling approach for output responses, could be 'None' (Default), 'standardize', 'minmax, or 'robminmax..

All outputs are written to disk in the same format as the input. These are:

Outputs:

- yhat -predictive mean
- ys2 -predictive variance
- 'nm -normative model
- Zdeviance scores
- Rho -Pearson correlation between true and predicted responses
- Rho -parametric p-value for this correlation
- mse -root mean squared error between true/predicted responses
- smse -standardised mean squared error

## PROTOCOL

- c Run analysis in the cloud using Google Colab

![Image](Rutherford2022b_artifacts/image_000006_2fabc7788d0d65979b38ae72fb2b6b773d976bfb708fcf6598cfe1b40c01dbb5.png)

© NormativeModelTutorial.ipynb

File Edit View Insert Runtime Tools

![Image](Rutherford2022b_artifacts/image_000007_028b2d12af7ba0fa3df7a5268fd61ba170a46aab7a9bc8172dc2abbb4d936e5e.png)

Table of contents

## | Predictive Clinical Neuroscience Toolkit

Step 0: Install necessary libraries &amp; grab data files

Step 1: Prepare covariate data

Step 2: Prepare brain data

'Step 3: Combine covariate &amp; cortical thickness dataframes

Step 4: Format dataframes to run normative models

Create train/test split

Save out each RO! to its own file

Step 5: Run normative model

Extract site indices

Basis expansion

Prepare output structures

Estimate the normative models

Step 6: Interpreting model performance

Fig. 3 | Overview of resources for running a normative modeling analysis. a , Detailed documentation, including installation instructions, input/output descriptions of all classes and functions implemented in the Python package, tutorials for all algorithms, frequently asked questions, a glossary explaining acronyms and other jargon, references to existing normative modeling literature, and a citation guide, is available at https://pcntoolkit.rea dthedocs.io/en/latest. b , Example of the documentation showing the required input and expected output of the main function used in the pcntoolkit software, the estimate function. c , All of the code and data used in this protocol are available to run in the cloud via Google Colab. Additional tutorials (shown under the tutorials header in a ) are also available to run in Google Colab.

## Data selection: covariate selection

The next choice should be regarding which covariates to include. One of the main criteria to include a covariate is the relevance to the posed research question. In normative modeling, usually we are interested in studying the deviations from the norm of the population; in other words, we are more interested in residuals. Thus, when we include a covariate in the design matrix for estimating the normative model, we are mainly interested in removing its effect from the residuals (thus, deviations) than investigating its effect on the neuroimaging variable. Normative modeling is a tool to study unknowns (that are encoded in the deviations). To do so, we need to /uniFB01 rst account for known variation in the data by regressing them out of the data (thus, we include the knowns in the covariates), and then we interpret the residual variation in the deviation scores. For example, if you want to know the effect of smoking on the ventral striatum, that is not confounded by other substance use, you should include substance use variables (e.g., drinks per week, etc.) in the covariate matrix, estimate the normative model and then correlate the ventral striatum deviation score (that has the effect of drinking removed from it) with smoking frequency. When pooling data from multiple sites, the available measures across sites may in /uniFB02 uence the selection of covariates because, ideally, the variables should be consistent across sites. For example, you should not use different versions of a cognitive test, as they could test for different dimensions of general cognitive ability. For neurodevelopmental or lifespan model, the suggested minimum covariates to include are age, sex, site (using random or /uniFB01 xed effects) and, optionally, a metric of data quality (i.e., mean framewise displacement or Freesurfer Euler number). Modeling site is very important; however, an exhaustive explanation is outside the scope of this protocol, but see refs. 20,21 for an in-depth account of modeling site variation. Diagnostic labels could also be included as covariates to utilize the variance explained by these labels without constraining the mapping to only re /uniFB02 ect case -control differences. Furthermore, additional biological covariates could also be included, such as blood biomarkers, or structural brain measures if predicting functional brain measures. Additional or alternative covariates may include other demographics (race, ethnicity, gender, education level, marital status, household income) and cognitive variables.

## Data selection: MRI modality and spatial resolution of brain data

Next, it is necessary to decide on the modality of brain imaging to model. In this protocol, we use cortical thickness and subcortical volume measurements from structural MRI (T1-weighted) images. However, other modalities such as resting-state and task-based fMRI or diffusion-weighted MRI could also be selected at this stage (data for these modalities are not provided with this protocol). The resolution of brain data is important to consider while keeping in mind the increasing computational complexity with modeling smaller units. Vertex or voxel-level modeling of brain data provides highresolution deviation maps. Still, region of interest (ROI) level modeling may allow for easier interpretation/visualization of the output deviation maps and will have a lower penalty in multiple comparison correction (if doing post hoc analysis) on the deviation maps. The PCNtoolkit can run models in parallel to speed up computation time; however, there is still a univariate nature, meaning a separate model is /uniFB01 t for each brain region. This univariate approach does not address the spatial autocorrelation 58 -65 or functional heterogeneity (functional misregistration) present in (f)MRI data 66 . Spatial autocorrelation refers to the complex spatial correlation patterns present in MRI data. Nearby regions are often more correlated than distant regions; thus, they are not statistically independent. Spatial correlations are dif /uniFB01 cult to model owing to their heterogeneity, complexity and high dimensionality with a limited sample size. Techniques such as Markov random /uniFB01 elds 62,63 , network/graph theory (topology) 58,59,64,65 and spatial Bayesian latent factor methods 61,67 have been applied to address the problem of spatial autocorrelation in raw or preprocessed MRI data. Progress in addressing spatial autocorrelation in the context of normative modeling has also been made in which Kronecker algebra and low-rank approximations are used to build multivariate normative models 68,69 . In the context of normative modeling, we recommend paying extra attention to image registration to properly model the functional regions, as the spatial overlap of regions across individuals is not guaranteed with functional areas. In addition to taking extra measures to align the fMRI data, rather than modeling single voxels or parcels (as is often done in structural MRI), it may be bene /uniFB01 cial to model brain networks as these features better capture the spatial patterns of functional units.

## Data preparation: preprocessing and quality checking

Example data have been curated and shared for the purposes of this protocol. As mentioned in the ' Data selection: MRI modality and spatial resolution of brain data ' section above, we use structural MRI and have run Freesurfer to extract cortical thickness and subcortical volume measures. If using other data than the provided protocol data (i.e., your own data), you will need to preprocess them accordingly and quality check the data to ensure only high-quality data are included. If you are new to working with MRI data, we recommend Andy ' s Brain Book 70 , which includes videos and code tutorials for most neuroimaging software (e.g., Freesurfer, FSL, SPM).

## Data preparation: set up computational environment

At this stage, you will create a Python virtual environment and install the required Python packages. Then you will clone the GitHub repository that contains all the code and data required to follow along with the Procedure. You can run the entire protocol in the cloud using Google Colab or choose to run the code on your own computer or server.

## Data preparation: format design matrix (site effects)

It is rare for a single scanning site to acquire large-enough samples that are an accurate representation of the general population. Therefore, it is common to pool data obtained across multiple MRI centers. Some projects, such as the ABCD study 71 , have begun to harmonize scanning protocols because multisite pooling was planned before data collection. In contrast, other projects, such as ENIGMA 72 , combine data post-collection and not have harmonized scanning sessions before data collection. If possible, to eliminate additional sources of variance, multisite pooled data should be preprocessed using identical pipelines and software versions. However, owing to data sharing restrictions and privacy concerns regarding health data, raw data may be unavailable, making pre- or post-data collection harmonization efforts impossible. Data harmonization techniques, such as COMBAT 73 -76 , aim to remove site-related variance from the data as a preprocessing step before further analyses are run. There are some issues with harmonization, principally that all sources of variance that are correlated with the batch effects (i.e., site-related variance) are removed, which can unintentionally remove important, unknown, clinically relevant variance from the data. COMBAT also requires that the user have access to all the data when harmonizing, which may have implications for data privacy. We therefore do not recommend users focus on data harmonization techniques when preparing their datasets for normative

modeling. Hierarchical Bayesian regression (HBR) 27,28 implemented in the PCNtoolkit has been thoroughly developed and tested to address these challenges when using multisite data in normative modeling. HBR estimates site-speci /uniFB01 c mean effects and variations in the normative model estimation stage using a Bayesian hierarchical model, which produces site-agnostic deviation scores ( z -statistics). This distinction between harmonization techniques (i.e., COMBAT) and HBR-normative modeling is very important when using deviation scores as features in subsequent interpretation analyses, as harmonization has been shown to overexaggerate con /uniFB01 dence in downstream analyses 77 .

## Data preparation: train -test split

While there are no hard rules for selecting the relative proportion of training and test data, some general guidelines that may help this decision can be considered. On the one hand, it is important to ensure the training set be suf /uniFB01 ciently large to model the target phenotype with suf /uniFB01 cient accuracy and precision. On the other hand, ensuring the test set is not too small is also important to provide suf /uniFB01 cient sensitivity to detect downstream differences (which may depend on the expected frequency of clinically relevant deviations in the test set). In practice a 70% train, 30% test split or 80% train, 20% test split often provides a reasonable balance between these competing objectives, but in certain applications it may be necessary to deviate from these recommendations. The main purpose of the train -test split is to establish out-of-sample generalizability and whether there is over- (or under-) /uniFB01 tting occurring. More important than the exact ratio of the train -test split, we believe it is critical to focus on preserving the sample characteristics across the train -test split. For example, it would not be sensible to model age ranges of childhood and adolescence in the reference cohort and have the test cohort consist of late-adulthood ages. This scenario would detect high deviations in this test set due to not properly modeling the target population. If you want to investigate the hypothesis that a certain clinical group (e.g., individuals with a psychosis diagnosis) have more extreme deviation patterns than a control group (individuals with no psychiatric diagnosis), you need to verify that it is because they are patients, not because they are in the test set. To verify this, it is important to also include some controls (from the same imaging site as the patients) in the test set. In other words, you cannot separate site variation from diagnostic variation if you do not have control reference data.

The train -test ratio decision naturally relates to the sample size requirements of the reference cohort mentioned in the ' Data selection: reference cohort inclusion criteria ' section above, and the same consideration of the context needs to be taken when creating the train -test split. Does this split align with the research question being asked? More speci /uniFB01 cally, does the training set adequately match the reference ( ' normative ' ) cohort, and does the testing set represent the target cohort in which deviations (from the reference cohort) will be interpreted? We discourage cross-validation, or iteratively resampling the dataset into train and test sets, unless the dataset is very small, and if it is used then practitioners should be aware of the problems it introduces. Ideally, the train -test split of the dataset will only be done once. While cross-validation is useful for testing stability and sensitivity of models to perturbations, it also leads to having multiple models that are not easy to combine and interpret, and it induces dependence between folds, which violates most parametric statistical tests 78 .

## Algorithm and modeling: algorithm selection

After the data have been carefully chosen and curated, it is time to move on to the normative modeling implementation. There are several algorithms for implementing a normative model, including Gaussian process regression (GPR) 79 , Bayesian linear regression (BLR) 25,67 , HBR 27,28 , generalized additive models of location, scale and shape (GAMLSS) 26 , neural processes 68 , random feature approximation 80 and quantile regression 81 , and many of these are implemented in the PCNtoolkit software package (Table 1). The algorithms have different properties depending on their ability to model nonlinear effects, scaling to large datasets (in terms of computation time), handling of random or /uniFB01 xed effects (e.g., to model site effects), their ability to model heteroscedastic or nonGaussian noise distributions and their suitability for use in a federated or decentralized learning environment. An overview of these algorithm implementations is covered in Table 1. GPR was widely used in the beginning phases of normative modeling, which can /uniFB02 exibly model nonlinear effects but does not computationally scale well when the training data increase (i.e., beyond a few thousand data points). In this work, we focus on BLR, which is highly scalable (fast computing time with large samples) and /uniFB02 exible (can be transferred to new sites not included in the training sample and can be combined with likelihood warping to model non-Gaussian effects). HBR is another appealing choice as it has been used to better address multisite datasets, allows for transfer learning (e.g., prediction for unseen sites) and can be estimated in a federated learning framework, which is useful if there are

## Materials

## Procedure

Table 1 | PCNtoolkit normative modeling algorithm overview

| Algorithm   | Implemented in PCNtoolkit?   | Transfer to new sites?   | Fast computing time with large sample sizes?   | Model non- Gaussianity?   | Federated learning framework?   |
|-------------|------------------------------|--------------------------|------------------------------------------------|---------------------------|---------------------------------|
| GPR a       | Yes                          | No                       | No                                             | No                        | No                              |
| HBR         | Yes                          | Yes                      | Yes                                            | Yes                       | Yes                             |
| BLR         | Yes                          | Yes                      | Yes                                            | Yes                       | No                              |
| GAMLSS b    | No                           | Yes                      | Yes                                            | Yes                       | No                              |

Random feature approximation and neural processes algorithms are not well documented in the PCNtoolkit and do not have tutorials available; thus, these algorithms are not included in the table and are only recommended for advanced users who can implement the code on their own. a The vanilla GPR algorithm implemented in the PCNtoolkit cannot model non-Gaussianity and does not scale well to large datasets. However, this is a question of implementation, and there are versions of GPs algorithms that satisfy these criteria 82,83 . b Implemented in R; see the GitHub repository at https:// github.com/dinga92/gamlss\_normative\_paper.

privacy concerns and/or sharing restrictions meaning data cannot easily be pooled at a single computing site.

## Evaluation and interpretation

The remaining steps, including evaluating the model performance, interpreting the model /uniFB01 t, and ideas for post hoc analysis of the normative modeling outputs, are covered in more detail in the Procedure.

## Equipment

- Computing infrastructure: a Linux computer or HPC (SLURM or Torque) with enough space to store the imaging data of the train and test set c CRITICAL If a Linux computer or server is unavailable, this protocol can also be run in Google Colab for free (https://colab.research.google.com/github/predictiveclinical-neuroscience/PCNtoolkit-demo/blob/main/tutorials/BLR\_protocol/BLR\_normativemodel\_ protocol.ipynb). If using Google Colab, only a computer with an internet connection and modern internet browser (e.g., Chrome or Firefox) installed is necessary.
- Python installation (https://www.python.org/downloads/)
- Recommended: Anaconda or virtual environment to manage the required Python packages (https:// www.anaconda.com/ or https://virtualenv.pypa.io/en/latest/)
- PCNtoolkit Python package version 0.20 (and dependencies) installed via pip (https://pcntoolkit. readthedocs.io/en/latest/pages/installation.html)
- Demographic and behavioral data used as predictor variables, e.g., age, sex/gender, site/scanner ID, race/ethnicity, cognition, data quality metric (Euler number if structural, mean framewise displacement if functional)
- Biological data to be modeled, e.g., structural MRI (cortical thickness, surface area, subcortical volume) and fMRI (parcellated task activation maps, resting-state networks). An example structural MRI dataset is provided on https://github.com/predictive-clinical-neuroscience/PCNtoolkit-demo/tree/main/data/

c CRITICAL The data selection stage (stage 1 in Fig. 2) does not require code, as it is more of a research question formulation stage (i.e., choosing inclusion criteria and what type of imaging modality to model). Data preprocessing (running Freesurfer) and quality checking have also already been performed before starting the Procedure, and code for running Freesurfer or other preprocessing is not included in this protocol. Thus, for this protocol, the Procedure begins at the ' Data preparation: set up computational environment ' stage. See ' Experimental design ' for guidance on the data selection stage and preprocessing if using different data than those provided with the protocol.

## Data preparation: prepare computational environment ● Timing 1 -3 min

- 1 Begin by cloning the GitHub repository using the following commands. This repository contains the necessary code and example data. Then install the Python packages using pip and import them

## PROTOCOL

into the Python environment (either Google Colab or using a local Python installation on your computer), as follows:

## git clone https://github.com/predictive-clinical-neuroscience/PCNtoolkit-demo.git

# set this path to the git cloned PCNtoolkit-demo repository --&gt; Uncomment whichever line you need for either running on your own computer or on Google Colab.

#os.chdir('/Users/saigerutherford/repos/PCNtoolkit-demo/BLR\_tutorial/')

# if running on your own computer, use this line (change the path to match where you cloned the repository)

#os.chdir('/content/PCNtoolkit-demo/BLR\_tutorial/') # if running on Google Colab, use this line import os

pip install -r requirements.txt

import pandas as pd

import numpy as np import matplotlib.pyplot as plt import seaborn as sns import joypy from sklearn.model\_selection import train\_test\_split from pcntoolkit.normative import estimate, evaluate from pcntoolkit.utils import create\_bspline\_basis, compute\_MSLL

## Data preparation: prepare covariate data ● Timing 5 -8 min

- 2 The dataset (downloaded in Step 1) includes a multisite dataset from the Human Connectome Project Young Adult study (https://humanconnectome.org/study/hcp-young-adult), and IXI (https://brain-development.org/ixi-dataset). It is also possible to use different datasets (i.e., your own data or additional public datasets) in this step. If using your own data here, it is recommended to load the example data to view the column names in order to match your data to this format. Read in the data /uniFB01 les using pandas, then merge the covariate (age and sex) data from each site into a single data frame (named cov), using the following commands. The columns of this covariate data frame represent the predictor variables. Additional columns may be added here, depending on the research question.

=

```
hcp pd.read_csv('data/HCP1200_age_gender.csv') ixi = pd.read_csv('data/IXI_age_gender.csv') cov = pd.merge(hcp, ixi, how = 'outer') sns.set(font_scale = 1.5, Style = 'darkgrid') sns.displot(cov, x = "age", hue = "site", multiple = "stack", Height = 6) cov.groupby(['site']).describe()
```

## Data preparation: prepare brain data ● Timing 10 -15 min

- 3 Next, format and combine the MRI data using the following commands. The example data contain cortical thickness maps estimated by running recon-all from Freesurfer (version 6.0). The dimensionality of the data was reduced by using ROIs from the Desikan -Killiany atlas. Including the Euler number (https://mathworld.wolfram.com/EulerCharacteristic.html) as a covariate is also recommended, as this is a proxy metric for data quality. The Euler number from each subject ' s recon-all output folder was extracted into a text /uniFB01 le and is merged into the cortical thickness data frame. The Euler number is site-speci /uniFB01 c; thus, to use the same exclusion threshold across sites, it is important to center the site by subtracting the site median from all subjects at a site. Then take the square root and multiply by -1, and exclude any subjects with a square root &gt;10.

hcpya = pd.read\_csv('data/HCP1200\_aparc\_thickness.csv') ixi = pd.read\_csv('data/IXI\_aparc\_thickness.csv') brain\_all = pd.merge(ixi, hcpya, how = 'outer')

```
hcp_euler = pd.read_csv('data/hcp-ya_euler.csv') ixi_euler = pd.read_csv('data/ixi_euler.csv') hcp_euler['site'] = 'hcp' ixi_euler['site'] = 'ixi' hcp_euler.dropna(inplace = True) ixi_euler.dropna(inplace = True) hcp_euler['rh_euler'] = hcp_euler['rh_euler'].astype(int) hcp_euler['lh_euler'] = hcp_euler['lh_euler'].astype(int) ixi_euler['rh_euler'] = ixi_euler['rh_euler'].astype(int) ixi_euler['lh_euler'] = ixi_euler['lh_euler'].astype(int) df_euler = pd.merge(ixi_euler, hcp_euler, how = 'outer') df_euler['avg_euler'] = df_euler[['lh_euler','rh_euler']].mean(axis = 1) df_euler.groupby(by = 'site').median() df_euler['site_median'] = df_euler['site'] df_euler['site_median'] = df_euler['site_median'].replace({'hcp':-43, 'ixi':-56}) df_euler['avg_euler_centered'] = df_euler['avg_euler'] -df_euler ['site_median'] df_euler['avg_euler_centered_neg'] = df_euler['avg_euler_centered']*-1 df_euler['avg_euler_centered_neg_sqrt'] = np.sqrt(np.absolute (df_euler['avg_euler_centered_neg'])) brain = pd.merge(df_euler, brain_all, how = 'inner') brain_good = brain.query('avg_euler_centered_neg_sqrt < 10')
```

c CRITICAL STEP If possible, data should be visually inspected to verify that the data inclusion is not too strict or too lenient. Subjects above the Euler number threshold should be manually checked to verify and justify their exclusion due to poor data quality. This is just one approach for automated QC used by the developers of the PCNtoolkit. Other approaches such as the ENIGMA QC pipeline (https://enigma.ini.usc.edu/protocols/imaging-protocols) or UK Biobank ' s QC pipeline 55 are also viable options for automated QC.

## Data preparation: check that subjects (rows) align across covariate and brain dataframes ● Timing 3 -5 min

- 4 The normative modeling function requires the covariate predictors and brain features to be in separate text /uniFB01 les. However, it is important to /uniFB01 rst (inner) merge them together, using the following commands, to con /uniFB01 rm that the same subjects are in each /uniFB01 le and that the rows (representing subjects) align. This requires that both data frames have ' subject\_id ' as a column name. Once this is con /uniFB01 rmed, exclude rows with NaN values and separate the brain features and covariate predictors into their own dataframes, using the commands below.

```
# make sure to use how = "inner" so that we only include subjects that have data in both the covariate and the cortical thickness /uniFB01 les all_data = pd.merge(brain_good, cov, how = 'inner') # Create a list of all the ROIs you want to run a normative model for roi_ids = ['lh_MeanThickness_thickness', 'rh_MeanThickness_thickness', 'lh_bankssts_thickness', 'lh_caudalanteriorcingulate_thickness', 'lh_superiorfrontal_thickness', 'rh_superiorfrontal_thickness'] from sklearn.model_selection import train_test_split all_data = all_data.dropna() all_data_features = all_data[[subset = roi_ids]] all_data_covariates = all_data[['age','sex','site']]
```

c CRITICAL STEP roi\_ids is a variable that represents which brain areas will be modeled and can be used to select subsets of the data frame if you do not wish to run models for the whole brain.

## Data preparation: add variable to model site/scanner effects ● Timing 3 -5 min

- 5 Currently, the different sites are coded in a single column (named ' site ' ) and are represented as a string data type. However, the PCNtoolkit requires binary variables. Use the pandas package as follows to address this, which has a built-in function, pd.get\_dummies, that takes in the string ' site ' column and dummy encodes the site variable so that there is now a column for each site and the columns contain binary variables (0 = not in this site, 1 = present in this site).

all\_data\_covariates = pd.get\_dummies(all\_data\_covariates, columns = ['site']) all\_data['Average\_Thickness'] = all\_data[['lh\_MeanThickness\_thickness', 'rh\_MeanThickness\_thickness']].mean(axis = 1)

## Data preparation: train -test split ● Timing 5 -10 min

- 6 In this example, we use 80% of the data for training and 20% for testing. Please carefully read the experimental design section on train -test split considerations when using your own data in this step. Using a function from scikit-learn ( train\_test\_split ), stratify the train -test split using the site variable to make sure that the train -test sets both contain data from all sites, using the following commands. Next, con /uniFB01 rm that your train and test arrays are the same size (columns), using the following commands. The rows (subjects) in the train and test arrays do not need to be the same size as there are different percentages of the sample in the train (80%) and test (20%) sets, but the columns represent the covariate and responses, which should be the same across train and test arrays. In other words, there are different numbers of subjects (rows) in the train and test sets, but each subject must have the same set of brain regions (responses) and covariate variables.

X\_train, X\_test, y\_train, y\_test train\_test\_split(all\_data\_covari-ates, all\_data\_features, stratify = all\_data['site'], test\_size = 0.2, random\_state = 42)

= tr\_cov\_size = X\_train.shape tr\_resp\_size = y\_train.shape te\_cov\_size = X\_test.shape te\_resp\_size = y\_test.shape print("Train covariate size is: ", tr\_cov\_size) print("Test covariate size is: ", te\_cov\_size) print("Train response size is: ", tr\_resp\_size) print("Test response size is: ", te\_resp\_size)

c CRITICAL STEP The model would not learn the site effects if all the data from one site were only in the test set. Therefore, we stratify the train -test split using the site variable.

- 7 When the data were split into train and test sets, the row index was not reset. This means that the row index in the train and test data frames still corresponds to the full data frame (before splitting the data occurred). The test set row index informs which subjects belong to which site, and this information is needed to evaluate per-site performance metrics. Resetting the row index of the train -test data frames /uniFB01 xes this issue. Then extract the site row indices to a list (one list per site) and create a list called site\_names that is used to decide which sites to evaluate model performance for, as follows:

x\_col\_names = ['age', 'sex', 'site\_hcp', 'site\_ixi'] X\_train = pd.read\_csv('data/covariate\_ /uniFB01 les/cov\_tr.txt', sep = '\t', header = None, names = x\_col\_names) X\_test = pd.read\_csv('data/covariate\_ /uniFB01 les/cov\_te.txt', sep = '\t', header = None, names = x\_col\_names) y\_train = pd.read\_csv('data/response\_ /uniFB01 les/resp\_tr.txt', sep = '\t', header = None) y\_test = pd.read\_csv('data/response\_ /uniFB01 les/resp\_te.txt', sep = '\t', header = None) X\_train.reset\_index(drop = True, inplace = True) X\_test.reset\_index(drop = True, inplace = True) y\_train.reset\_index(drop = True, inplace = True)

= =

```
y_test.reset_index(drop True, inplace True) hcp_idx = X_test.index[X_test['site_hcp'] == 1].to_list() ixi_idx = X_test.index[X_test['site_ixi'] == 1].to_list()
```

```
# Save the site indices into a single list
```

```
sites = [hcp_idx, ixi_idx]
```

# Create a list with sites names to use in evaluating per-site metrics site\_names = ['hcp', 'ixi']

## Data preparation: set up output directories ● Timing 1 -3 min

- 8 Save each brain region to its own text /uniFB01 le (organized in separate directories) using the following commands, because for each response variable, Y (e.g., brain region) we /uniFB01 t a separate normative model.

```
for c in y_train.columns: y_train[c].to_csv('resp_tr_' + c + '.txt', header = False, index = False) X_train.to_csv('cov_tr.txt', sep = '\t', header = False, index = False) y_train.to_csv('resp_tr.txt', sep = '\t', header = False, index = False) for c in y_test.columns: y_test[c].to_csv('resp_te_' + c + '.txt', header = False, index = False) X_test.to_csv('cov_te.txt', sep = '\t', header = False, index = False) y_test.to_csv('resp_te.txt', sep = '\t', header = False, index = False) ! if [[! -e data/ROI_models/]]; then mkdir data/ROI_models; /uniFB01 ! if [[! -e data/covariate_ /uniFB01 les/]]; then mkdir data/covariate_ /uniFB01 les; /uniFB01 ! if [[! -e data/response_ /uniFB01 les/]]; then mkdir data/response_ /uniFB01 les; /uniFB01 ! for i in `cat /content/PCNtoolkit-demo/data/roi_dir_names`; do cd data/ROI_models; mkdir ${i}; cd../../; cp resp_tr_${i}.txt data/ROI_ models/${i}/resp_tr.txt; cp resp_te_${i}.txt data/ROI_models/${i}/ resp_te.txt; cp cov_tr.txt data/ROI_models/${i}/cov_tr.txt; cp cov_te.txt data/ROI_models/${i}/cov_te.txt; done ! mv resp_*.txt data/response_ /uniFB01 les/ ! mv cov_t*.txt data/covariate_ /uniFB01 les/
```

## Algorithm and modeling: basis expansion using B-splines ● Timing 1 -3 min

- 9 Now, set up a B-spline basis set that allows us to perform nonlinear regression using a linear model, using the following commands. This basis is deliberately chosen to not to be too /uniFB02 exible so that it can only model relatively slowly varying trends. To increase the /uniFB02 exibility of the model, you can change the parameterization (e.g., by adding knot points to the B-spline basis or increasing the order of the interpolating polynomial). Note that, in the neuroimaging literature, it is more common to use a polynomial basis expansion for this. Piecewise polynomials such as B-splines are superior to polynomial basis expansions because they do not introduce a global curvature. For further details on the use of B-splines, see Fraza et al. 25 .

```
# Create a cubic B-spline basis (used for regression) xmin = 10 # xmin & xmax are the boundaries for ages of participants in the dataset xmax = 95 B = create_bspline_basis(xmin, xmax) # create the basis expansion for the covariates for roi in roi_ids: print('Creating basis expansion for ROI:', roi) roi_dir = os.path.join(data_dir, roi) os.chdir(roi_dir) # create output dir os.makedirs(os.path.join(roi_dir,'blr'), exist_ok = True) # load train & test covariate data matrices
```

## PROTOCOL

```
X_tr = np.loadtxt(os.path.join(roi_dir, 'cov_tr.txt')) X_te = np.loadtxt(os.path.join(roi_dir, 'cov_te.txt')) # add intercept column X_tr = np.concatenate((X_tr, np.ones((X_tr.shape[0],1))), axis = 1) X_te = np.concatenate((X_te, np.ones((X_te.shape[0],1))), axis = 1) np.savetxt(os.path.join(roi_dir, 'cov_int_tr.txt'), X_tr) np.savetxt(os.path.join(roi_dir, 'cov_int_te.txt'), X_te) # create Bspline basis set Phi = np.array([B(i) for i in X_tr[:,0]]) Phis = np.array([B(i) for i in X_te[:,0]]) X_tr = np.concatenate((X_tr, Phi), axis = 1) X_te = np.concatenate((X_te, Phis), axis = 1) np.savetxt(os.path.join(roi_dir, 'cov_bspline_tr.txt'), X_tr) np.savetxt(os.path.join(roi_dir, 'cov_bspline_te.txt'), X_te)
```

## Algorithm and modeling: estimate normative model ● Timing 3 -5 min per model (multiply by number of ROIs/models)

- 10 Set up a variable (data\_dir) that speci /uniFB01 es the path to the ROI directories that were created in Step 7. Initiate two empty pandas data frames where the evaluation metrics are the column names, as follows; one will be used for overall test set evaluation (blr\_metrics), and one will be used for sitespeci /uniFB01 c test set evaluation (blr\_site\_metrics). After the normative model has been estimated, these data frames will be saved as individual csv /uniFB01 les.

# set this path to wherever your ROI\_models folder is located (where you copied all of the covariate &amp; response text /uniFB01 les to in Step 4) data\_dir = '/Users/saigerutherford/repos/PCNToolkit-demo/data/ROI\_ models/'

# Create pandas dataframes with header names to save out the overall and per-site model evaluation metrics blr\_metrics = pd.DataFrame(columns = ['ROI', 'MSLL', 'EV', 'SMSE', 'RMSE', 'Rho'])

blr\_site\_metrics = pd.DataFrame(columns = ['ROI', 'site', 'y\_mean', 'y\_var', 'yhat\_mean',

'yhat\_var', 'MSLL', 'EV', 'SMSE', 'RMSE', 'Rho'])

- 11 Estimate the normative models using a for loop to iterate over brain regions, using the following commands. The estimate function uses a few speci /uniFB01 c arguments that are worth commenting on:
- alg = ' blr ' : speci /uniFB01 es we should use BLR. See Table 1 for other available algorithms
- optimizer = ' powell ' : use Powell ' s derivative-free optimization method (faster in this case than L-BFGS)
- savemodel = False: do not write out the /uniFB01 nal estimated model to disk
- saveoutput = False: return the outputs directly rather than writing them to disk
- standardize = False: do not standardize the covariates or response variables

An important consideration is whether to rescale or standardize the covariates or responses. While this generally has only a minor effect on the /uniFB01 nal model accuracy, it has implications for the interpretation of models and how they are con /uniFB01 gured. If the covariates and responses are both standardized (standardize = True), the model will return standardized coef /uniFB01 cients. If (as in this case) the response variables are not standardized (standardized = False), then the scaling of both covariates and responses will be re /uniFB02 ected in the estimated coef /uniFB01 cients. Also, under the linear modeling approach employed here, if the coef /uniFB01 cients are unstandardized and do not have a zero mean, it is necessary to add an intercept column to the design matrix (this is done above in Step 9 (B-spline)).

```
# Loop through ROIs for roi in roi_ids: print('Running ROI:', roi) roi_dir = os.path.join(data_dir, roi) os.chdir(roi_dir)
```

# con /uniFB01 gure the covariates to use. Change *\_bspline\_* to *\_int\_* to cov\_ /uniFB01 le\_tr = os.path.join(roi\_dir, 'cov\_bspline\_tr.txt') cov\_ /uniFB01 le\_te = os.path.join(roi\_dir, 'cov\_bspline\_te.txt')

# load train &amp; test response /uniFB01 les resp\_ /uniFB01 le\_tr = os.path.join(roi\_dir, 'resp\_tr.txt') resp\_ /uniFB01 le\_te = os.path.join(roi\_dir, 'resp\_te.txt')

# run a basic model yhat\_te, s2\_te, nm, Z, metrics\_te = estimate(cov\_ /uniFB01 le\_tr, resp\_ /uniFB01 le\_tr, testresp = resp\_ /uniFB01 le\_te, testcov = cov\_ /uniFB01 le\_te, alg = 'blr', optimizer = 'powell', savemodel = False, saveoutput = False, standardize = False)

# display and save metrics print('EV = ', metrics\_te['EXPV'][0]) print('RHO = ', metrics\_te['Rho'][0]) print('MSLL = ', metrics\_te['MSLL'][0]) blr\_metrics.loc[len(blr\_metrics)] = [roi, metrics\_te['MSLL'][0], metrics\_te['EXPV'][0], metrics\_te['SMSE'][0], metrics\_te['RMSE'][0], metrics\_te['Rho'][0]]

# Compute metrics per site in test set, save to pandas df

# load true test data

X\_te = np.loadtxt(cov\_ /uniFB01 le\_te)

y\_te = np.loadtxt(resp\_ /uniFB01 le\_te)

y\_te = y\_te[:, np.newaxis] # make sure it is a 2-d array

# load training data (required to compute the MSLL) y\_tr = np.loadtxt(resp\_ /uniFB01 le\_tr) y\_tr = y\_tr[:, np.newaxis]

for num, site in enumerate(sites):

y\_mean\_te\_site = np.array([[np.mean(y\_te[site])]]) y\_var\_te\_site = np.array([[np.var(y\_te[site])]]) yhat\_mean\_te\_site = np.array([[np.mean(yhat\_te[site])]]) yhat\_var\_te\_site = np.array([[np.var(yhat\_te[site])]])

metrics\_te\_site = evaluate(y\_te[site], yhat\_te[site], s2\_te[site], y\_mean\_te\_site, y\_var\_te\_site)

site\_name = site\_names[num]

blr\_site\_metrics.loc[len(blr\_site\_metrics)]

```
= [roi, site_names[num], y_mean_te_site[0], y_var_te_site[0], yhat_mean_te_site[0], yhat_var_te_site[0], metrics_te_site ['MSLL'][0], metrics_te_site ['EXPV'][0], metrics_te_site ['SMSE'][0],
```

## PROTOCOL

```
metrics_te_site ['RMSE'][0], metrics_te_site ['Rho'][0]]
```

c CRITICAL STEP This code fragment will loop through each ROI in the roi\_ids list (created in Step 4) using BLR and evaluate the model on the independent test set. In principle, we could estimate the normative models on the whole data matrix at once (e.g., with the response variables stored in a n\_subjects by n\_brain\_measures NumPy array or a text /uniFB01 le instead of saved out into separate directories). However, running the models iteratively gives some extra /uniFB02 exibility in that it does not require that the included subjects are the same for each of the brain measures.

## Evaluation and interpretation: evaluate normative model performance ● Timing 5 -10 min

12 In Step 11, when we looped over each ROI in the roi\_ids list (created in Step 4) and evaluated the normative model on the independent test set, it also computed the evaluation metrics such as the explained variance, mean standardized log loss and Pearson correlation between true and predicted test responses. The evaluation metrics were calculated for the full test set and calculated separately for each scanning site. The metrics were saved to a csv /uniFB01 le. In this step, load the evaluation metrics into a pandas data frame and use the describe function to show the range, mean and standard deviation of each of the evaluation metrics, using the following commands. Table 2 shows how to interpret the ranges/directions of good model /uniFB01 t.

```
metrics_te(['EXPV']).describe() metrics_te(['MSLL']).describe() metrics_te(['SMSE']).describe() metrics_te(['Rho']).describe() metrics_te_site(['EV']).describe() metrics_te_site(['MSLL']).describe() metrics_te_site(['SMSE']).describe() metrics_te_site(['Rho']).describe()
```

## Evaluation and interpretation: visualize normative model outputs ● Timing 15 -20 min

- 13 There are several ways to visualize the evaluation metrics from Step 12. There are typically many models /uniFB01 t across the different brain regions, and it can be a lot of effort to keep track of the performance across all the brain regions. Data visualization will help to understand if there are any emerging patterns and /uniFB01 nd if there are any brain areas (or certain sites) where the model does not /uniFB01 t well. Summarize the deviation scores in the test set by counting how many subjects have an ' extreme ' deviation (either positive or negative), and visualize the count of extreme negative and positive deviations by plotting them on a 3D brain plot, using a separate Python notebook (https://github.com/predictive-clinicalneuroscience/PCNtoolkit-demo/blob/main/tutorials/BLR\_protocol/visualizations.ipynb)

## Evaluation and interpretation: post hoc analysis ideas using normative modeling outputs ● Timing 1 -2 h

- 14 There are many interesting analyses that can be conducted using the outputs of normative modeling (deviation scores). An in-depth tutorial on each of these analyses is outside the scope of this protocol. However, on GitHub (https://github.com/predictive-clinical-neuroscience/PCNtoolkitdemo/blob/main/tutorials/BLR\_protocol/post\_hoc\_analysis.ipynb), we include code examples (Python notebooks that can be run via Colab) of the following post hoc analyses:
- Using deviation scores as predictors in a regression and classi /uniFB01 cation and comparing the performance with using the true data as predictors. Code for implementing several common predictive modeling frameworks (that are mentioned in comparison with other methods section) is provided. Deviation scores from normative modeling could be used as input features to any of these predictive modeling frameworks
- Using a pretrained normative model and transferring it to a new, unseen dataset
- Classical case -control testing (univariate t -tests) on deviation maps compared with univariate t -tests on the true data

## Table 2 | Normative model metrics

| Variable name     | Full name                                                | De /uniFB01 nition                                                                                                                                                                                                                                                | Interpretation                                                               | Individual or summary?   |
|-------------------|----------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------|--------------------------|
| y d               | True data                                                | -                                                                                                                                                                                                                                                                 | -                                                                            | Individual               |
| ^ y d             | Predictive mean                                          | -                                                                                                                                                                                                                                                                 | -                                                                            | Individual               |
| σ 2 d             | Predictive noise variance                                | Represents uncertainty in the data                                                                                                                                                                                                                                | -                                                                            | Individual               |
| σ 2 /C3 /C0 /C1 d | Predictive modeling variance                             | Represents uncertainty in model estimation                                                                                                                                                                                                                        | -                                                                            | Individual               |
| Z                 | Deviation score                                          | A statistical estimate ( Z -score) of how much each subject deviates from the normative range                                                                                                                                                                     | Z > 2 ' extreme ' positive deviation Z < - 2 ' extreme ' negative deviation  | Individual               |
| Rho               | Pearson correlation between true and predicted responses | A measure of linear correlation between true and predicted responses. It is the ratio between the covariance of true and predicted values and the product of their standard deviations                                                                            | Ranges between - 1 and 1. Closer to 1 = better model performance             | Summary                  |
| pRho              | Parametric P value for the Pearson correlation           | The probability of obtaining test results at least as extreme as the results actually observed, under the assumption that the null hypothesis is true                                                                                                             | Ranges between 0 and 1. Closer to 0 = more statistically signi /uniFB01 cant | Summary                  |
| SMSE              | Standardized mean squared error                          | The square root of the squared residual between the mean prediction and the target at each test point, averaged over samples in the test set, normalized by the variance of the targets in the test set                                                           | Closer to 0 = better (more accurate) model performance                       | Summary                  |
| EV                | Explained variance                                       | The proportion to which the predicted value accounts for the variance of the true value. Sensitive to the mean /uniFB01 t, dependent on /uniFB02 exibility of the model                                                                                           | Closer to 1 = better model performance                                       | Summary                  |
| MSLL              | Mean standardized log loss                               | The log loss minus the loss that would be obtained under the trivial model, which predicts using a Gaussian with the mean and variance of the training data, averaged over the test set. Sensitive to the variance, penalizes the /uniFB02 exibility of the model | More negative = better model performance                                     | Summary                  |

The ' Individual or summary? ' column refers to whether there is a value for every subject or if the metric is summarized across all subjects. For summary metrics, there is one value per brain region (model), and for individual metrics there are n\_subjects × n\_brain\_regions values.

## Troubleshooting

We reiterate that there is additional documentation available online at https://pcntoolkit.readthedocs. io/en/latest, including additional tutorials for other algorithm implementations (GPR and HBR), a glossary to clarify the jargon associated with the software, a reference guide with links to normative modeling publications and a frequently-asked-questions page where many common errors (and their solutions) are discussed in detail. The problems encountered when troubleshooting a normative modeling analysis can fall into three categories: computing errors, data issues, and misunderstanding or misinterpreting the outputs.

The normative modeling portion of this protocol (including evaluation and visualization) can be completed in ~57 -72 min. If you use the additional code for post hoc analysis of the normative modeling outputs (Step 14), add ~1 -2 h to the estimated normative modeling time. These timing estimates are based on use of the Google Colab platform to run the code. Running this protocol on your own computer (on which you need to install Python and dependencies) will add time to the protocol.

There are multiple end products created from running a normative model analysis. First, the evaluation metrics for each model (brain region) are saved to a /uniFB01 le. In this protocol, we saved the metrics to a CSV /uniFB01 le format; however, in the pcn.estimate() function you could set the argument ' binary = True ' , which would save the metrics in pickle (.pkl) format. Pickle format is good to use if you are

## Timing

## Anticipated results

## NATURE PROTOCOLS

## PROTOCOL

Fig. 4 | Visualization of normative model evaluation metrics. a , A ridge plot showing the distribution across all brain regions of the standardized mean squared error (SMSE), an evaluation metric that represents accuracy, visualized for each site in the test set. Visualizing for each test site can help identify if there are sites where the model is performing poorly. Ideally, the distribution will be Gaussian and look similar across all sites. Small shifts in the mean across sites, such as those shown here, are to be expected and are acceptable. b , Explained variance is shown for cortical thickness of every brain region in the Destrieux parcellation and volume of subcortical regions. Visualizing the evaluation metrics in brain space helps to identify patterns and see the big picture. c , The number of extreme deviations (both positive and negative) is counted for each individual in the test set; group ID is used to plot the distribution of the extreme deviation count for each group. A statistical test can be done on the count to determine if there is a signi /uniFB01 cant difference between groups. Testing group differences in the count of deviations does not require there to be spatial overlap of the deviations within the group (i.e., this test can account for within-group heterogeneity of deviations). d , The normative trajectory for an example brain region (lateral ventricle) showing age in years ( x axis) versus the predicted volume in mm 3 ( y axis). The centiles of variation are shown by the lines and shaded con /uniFB01 dence intervals. Each subject in the test set is plotted as a single point. e , f , Extreme deviations, separated into positive ( e ) and negative ( f ), are summarized for each group. For each brain region, the number of subjects with an extreme deviation in that region is counted, then divided by the group sample size, to show the percent of subjects with an extreme deviation. These visualizations demonstrate the bene /uniFB01 t of normative modeling as there is within-group heterogeneity that other methods (i.e., case -control group difference testing) are not equipped to handle. HC, controls; MDD, major depressive disorder; SZ, schizophrenia; SAD, social anxiety disorder; EP, early psychosis.

![Image](Rutherford2022b_artifacts/image_000008_5c2b67c3651225ce8119b887ab6e1a81a8413e457b2b83b8e4ffd3f2c1b16951.png)

estimating many models in parallel on a large dataset, as it is faster because it avoids reading/writing intermediate text /uniFB01 les. These metrics are further summarized into per-site metrics to check model /uniFB01 t for each site included in the test set. The short and full names of the evaluation metrics and a brief interpretation guide are summarized in Table 2. The evaluation metrics can be visualized in numerous formats, histograms/density plots, scatter plots with /uniFB01 tted centiles, or brain-space visualizations. Several examples of these visualizations are shown in Fig. 4, and code for creating these plots is shared on GitHub. Quality checking the normative model evaluation metrics should be done to ensure proper model estimation. If a model /uniFB01 ts well to the data, the evaluation metrics should follow a Gaussian distribution. The model estimation (Step 11) should properly handle confounding site effects; nevertheless, it is also a good idea to check per-site metrics to make sure the model is /uniFB01 tting all sites equally well and that there are no obvious site outliers. In addition to the summary level evaluation metrics, there are also many individual metrics (one value per subject for each model/brain region). These individual-level outputs can be very helpful for interpretation because they precisely quantify the uncertainty of each individual predicted value at every location across the brain. If a given individual is identi /uniFB01 ed as having an ' extreme ' deviation, and there is low uncertainty, you can be con /uniFB01 dent this is a biologically valid /uniFB01 nding and not due to modeling errors. Vice versa, if there are extreme deviations and high levels of uncertainty, more caution should be given to interpretating these results and the deviations may be due to modeling errors rather than true biological variation. The uncertainty estimates are separated into two components (noise and modeling, described in Table 2) to help pinpoint the sources of uncertainty.

A bene /uniFB01 t of the PCNtoolkit software for normative modeling, that sets our approach apart from other normative modeling implementations 84 , is the /uniFB01 ne-scale resolution allowed by the model. Other normative modeling work 84 has focused on modeling gross features such as total brain volume or gray matter volume, which is not adequate for normative modeling applied to mental health conditions and neurodevelopmental disorders, where the effects are subtle and widespread (individuals within a patient group tend to deviate in different regions; Fig. 4e,f) across the cortex and subcortex, and averaging over large brain areas usually overlooks these elusive psychiatric effects. This resolution also allows for a better mechanistic understanding because you can quantify the deviation and associated uncertainty for each individual with high spatial precision.

Reliability (the extent to which a measurement gives results that are very consistent) and validity (the degree to which a measurement measures what it is supposed to measure) are important constructs to keep in mind when interpreting results. In recent work, reliability of normative modeling in schizophrenia and bipolar disorder using structural MRI measures was established via replication 37 . Validity is arguably more challenging to assess but should be established by means of out-of-sample model /uniFB01 t. In other recent work, normative models were /uniFB01 t using a lifespan (age 3 -100 years) big data sample ( N = 58,836) and carefully tested out of sample (variance explained, skewness, kurtosis and standardized mean squared error) showing excellent model /uniFB01 t (12 -68% variance explained) in an independent test set from a sample (and site) that was not included in the training set 85 . This work suggests validity, but this is an ongoing evaluation, and out-of-sample model /uniFB01 t must always be considered and reported.

## Computing errors

The computing errors might involve Python or the computer hardware. Potential Python errors may include installation of Python or installation of the necessary packages and their dependencies. We recommend using Anaconda to install Python 3.8 (required for this protocol) on your system, and the use of a virtual environment for the PCNtoolkit to ensure that the packages required for normative modeling do not interfere with other Python versions and packages you may have installed on your system. In general, it is good to have a virtual environment setup for each project or analysis. If you are unfamiliar with setting up virtual environments, and run into issues with Python, it is always an option to run the analysis in the cloud via Colab, which eliminates the need to set up Python on your own system. Hardware problems might include lack of memory to store the data or models running very slowly owing to outdated hardware. These hardware errors do not have an easy solution, and we recommend using Google Colab to run normative modeling analysis if your personal computer or server is very slow or lacks the storage space.

## Data issues

Data issues that may be encountered are data missing not at random (see ' Experimental design ' regarding caution using data imputation), improperly coded data (i.e., strings instead of integers or /uniFB02 oats, NaN values coded incorrectly), collinearity of columns in the covariate design matrix or outlier data that do not make biological sense (i.e., negative cortical thickness values, negative age values). While these data errors can be incredibly frustrating to troubleshoot, they can typically be /uniFB01 xed by careful quality checking of the input data and removal of bad ROIs or subjects as needed.

## Interpretation confusion

Finally, an example of interpretation confusion may be poor model performance on a certain brain region or site. This can usually be addressed by returning to the input data for additional quality checking to con /uniFB01 rm that the poor performance is not due to data quality issues. If there are no data quality issues, then it may be the reality that the model does not /uniFB01 t some brain regions well, and you may want to consider including additional covariates in the model to help explain more variance. Another interpretation confusion may arise when seeing negative explained variance values. When testing out of sample, the explained variance is not restricted to being positive; if it is negative, this means that the model /uniFB01 t is very poor (it is worse than an intercept-only model).

## Reporting Summary

Further information on research design is available in the Nature Research Reporting Summary linked to this article.

## References

## Data availability

All data used in this protocol are available on GitHub (https://github.com/predictive-clinicalneuroscience/PCNtoolkit-demo/tree/main/tutorials/BLR\_protocol) and Zenodo 86 (https://zenodo. org/record/5592153#.YjL7PY\_P2UI) in csv. /uniFB01 les. We also include a template csv. /uniFB01 le to help format user ' s own data into the correct form for running the protocol using their own dataset.

## Code availability

All code is available on GitHub (https://github.com/predictive-clinical-neuroscience/PCNtoolkitdemo/tree/main/tutorials/BLR\_protocol) in the format of Python notebooks that can be run in the cloud (for free) using Google Colab (https://colab.research.google.com/github/predictive-clinicalneuroscience/PCNtoolkit-demo/blob/main/tutorials/BLR\_protocol/BLR\_normativemodel\_protocol. ipynb). We have also shared the GitHub repository on Zenodo (https://zenodo.org/record/5592153#. YjL7PY\_P2UI) to create a citable DOI for this software that also allows versions that are necessary as additional code and tutorials may be added over time 86 .

1. Wang, D. et al. Parcellating cortical functional networks in individuals. Nat. Neurosci. 18 , 1853 -1860 (2015).
2. Finn, E. S. &amp; Constable, R. T. Individual variation in functional brain connectivity: implications for personalized approaches to psychiatric disease. Dialogues Clin. Neurosci. 18 , 277 -287 (2016).
3. Braga, R. M. &amp; Buckner, R. L. Parallel interdigitated distributed networks within the individual estimated by intrinsic functional connectivity. Neuron 95 , 457 -471 (2017).
4. Poldrack, R. A. Precision neuroscience: dense sampling of individual brains. Neuron 95 , 727 -729 (2017).
5. Vanderwal, T. et al. Individual differences in functional connectivity during naturalistic viewing conditions. Neuroimage 157 , 521 -530 (2017).
6. Braun, U. et al. From maps to multi-dimensional network mechanisms of mental disorders. Neuron 97 , 14 -31 (2018).
7. Gratton, C. et al. De /uniFB01 ning individual-speci /uniFB01 c functional neuroanatomy for precision psychiatry. chiatry 88 , 28 -39 (2020).
8. Biol. Psy-
8. Hyman, S. E. Can neuroscience be integrated into the DSM-V? Nat. Rev. Neurosci. 8 , 725 -732 (2007).
9. Insel, T. et al. Research domain criteria (RDoC): toward a new classi /uniFB01 cation framework for research on mental disorders. Am. J. Psychiatry 167 , 748 -751 (2010).
10. Michelini, G., Palumbo, I. M., DeYoung, C. G., Latzman, R. D. &amp; Kotov, R. Linking RDoC and HiTOP: a new interface for advancing psychiatric nosology and neuroscience. Clin. Psychol. Rev. 86 , 102025 (2021).
11. Narrow, W. E. &amp; Kuhl, E. A. Dimensional approaches to psychiatric diagnosis in DSM-5. J. Ment. Health Policy Econ. 14 , 197 -200 (2011).
12. Cuthbert, B. N. &amp; Insel, T. R. Toward the future of psychiatric diagnosis: the seven pillars of RDoC. BMC Med. 11 , 126 (2013).
13. Sanislow, C. A. RDoC at 10: changing the discourse for psychopathology. World Psychiatry 19 , 311 -312 (2020).
14. Kotov, R. et al. The Hierarchical Taxonomy of Psychopathology (HiTOP): a dimensional alternative to traditional nosologies. J. Abnorm. Psychol. 126 , 454 -477 (2017).
15. Kotov, R. et al. The Hierarchical Taxonomy of Psychopathology (HiTOP): a quantitative nosology based on consensus of evidence. Annu. Rev. Clin. Psychol. 17 , 081219 -093304 (2021).
16. Haro, J. M. et al. ROAMER: roadmap for mental health research in Europe. Int. J. Methods Psychiatr. Res. 23 , 1 -14 (2014).
17. Schumann, G. et al. Strati /uniFB01 ed medicine for mental disorders. Eur. Neuropsychopharmacol. 24 , 5 -50 (2014).

18.

Feczko, E. et al. The heterogeneity problem: approaches to identify psychiatric subtypes.

Trends Cogn. Sci.

23

,

-

584

601 (2019).

19. Shen, X. et al. Using connectome-based predictive modeling to predict individual behavior from brain connectivity. Nat. Protoc. 12 , 506 -518 (2017).
20. Sripada, C. et al. Basic units of inter-individual variation in resting state connectomes. Sci. Rep. 9 , 1900 (2019).
21. Woo, C.-W., Chang, L. J., Lindquist, M. A. &amp; Wager, T. D. Building better biomarkers: brain models in translational neuroimaging. Nat. Neurosci. 20 , 365 -377 (2017).
22. Marquand, A. F. et al. Conceptualizing mental disorders as deviations from normative functioning. Mol. Psychiatry 24 , 1415 -1424 (2019).
23. Gau, R. et al. Brainhack: developing a culture of open, inclusive, community-driven neuroscience. Neuron 109 , 1769 -1775 (2021).
24. Olah, C. &amp; Carter, S. Research debt. Distill 2 , e5 (2017).
25. Fraza, C. J., Dinga, R., Beckmann, C. F. &amp; Marquand, A. F. Warped Bayesian linear regression for normative modelling of big data. Neuroimage 245 , 118715 (2021).
26. Dinga, R. et al. Normative modeling of neuroimaging data using generalized additive models of location scale and shape . Preprint at bioRxiv https://doi.org/10.1101/2021.06.14.448106 (2021).

27. Kia, S. M. et al. Hierarchical Bayesian regression for multi-site normative modeling of neuroimaging data. In Medical Image Computing and Computer Assisted Intervention -MICCAI 2020 (eds. Martel, A. L. et al.) 699 -709 (Springer International, 2020); https://doi.org/10.1007/978-3-030-59728-3\_68
28. Kia, S. M. et al. Federated multi-site normative modeling using hierarchical Bayesian regression. Preprint at bioRxiv https://doi.org/10.1101/2021.05.28.446120 (2021).
29. Floris, D. L. et al. Atypical brain asymmetry in autism -a candidate for clinically meaningful strati /uniFB01 cation. Biol. Psychiatry Cogn. Neurosci. Neuroimaging https://doi.org/10.1016/j.bpsc.2020.08.008 (2020).
30. Zabihi, M. et al. Dissecting the heterogeneous cortical anatomy of autism spectrum disorder using normative models. Biol. Psychiatry Cogn. Neurosci. Neuroimaging 4 , 567 -578 (2019).
31. Zabihi, M. et al. Fractionating autism based on neuroanatomical normative modeling. Transl. Psychiatry 10 , 1 -10 (2020).
32. Wolfers, T. et al. Individual differences v. the average patient: mapping the heterogeneity in ADHD using normative models. Psychol. Med. 50 , 314 -323 (2020).
33. Wolfers, T. et al. Re /uniFB01 nement by integration: aggregated effects of multimodal imaging markers on adult ADHD. J. Psychiatry Neurosci. 42 , 386 -394 (2017).
34. Verdi, S., Marquand, A. F., Schott, J. M. &amp; Cole, J. H. Beyond the average patient: how neuroimaging models can address heterogeneity in dementia. Brain https://doi.org/10.1093/brain/awab165 (2021).
35. Wolfers, T. et al. Replicating extensive brain structural heterogeneity in individuals with schizophrenia and bipolar disorder. Human Brain Mapp. https://doi.org/10.1002/hbm.25386 (2020).
36. Wolfers, T. et al. Mapping the heterogeneous phenotype of schizophrenia and bipolar disorder using normative models. JAMA Psychiatry 75 , 1146 -1155 (2018).
37. Wolfers, T. et al. Replicating extensive brain structural heterogeneity in individuals with schizophrenia and bipolar disorder. Hum. Brain Mapp. 42 , 2546 -2555 (2021).
38. Sripada, C., Angstadt, M., Rutherford, S. &amp; Taxali, A. Brain network mechanisms of general intelligence. Preprint at bioRxiv https://doi.org/10.1101/657205 (2019).
39. Sripada, C. et al. Brain Connectivity Patterns in Children Linked to Neurocognitive Abilities . Preprint at bioRxiv https://doi.org/10.1101/2020.09.10.291500 (2020).
40. Rosenberg, M. D. et al. A neuromarker of sustained attention from whole-brain functional connectivity. Nat. Neurosci. 19 , 165 -171 (2015).
41. Rosenberg, M. D. et al. Functional connectivity predicts changes in attention observed across minutes, days, and months. Proc. Natl Acad. Sci. USA 117 , 3797 -3807 (2020).
42. Marquand, A. F., Haak, K. V. &amp; Beckmann, C. F. Functional corticostriatal connection topographies predict goal directed behaviour in humans. Nat. Hum. Behav . 1 , 0146 (2017).
43. Marquand, A. et al. Quantitative prediction of subjective pain intensity from whole-brain fMRI data using Gaussian processes. Neuroimage 49 , 2178 -2189 (2010).
44. Wager, T. D. et al. An fMRI-based neurologic signature of physical pain. N. Engl. J. Med. 368 , 1388 -1397 (2013).
45. Sripada, C., Angstadt, M., Rutherford, S., Taxali, A. &amp; Shedden, K. Toward a ' treadmill test ' for cognition: improved prediction of general cognitive ability from the task activated brain. Hum. Brain Mapp. 41 , 3186 -3197 (2020).
46. Taxali, A., Angstadt, M., Rutherford, S. &amp; Sripada, C. Boost in test -retest reliability in resting state fMRI with predictive modeling. Cereb. Cortex 31 , 2822 -2833 (2021).
47. Finn, E. S. et al. Functional connectome /uniFB01 ngerprinting: identifying individuals using patterns of brain connectivity. Nat. Neurosci. 18 , 1664 -1671 (2015).
48. Wang, H.-T. et al. Finding the needle in a high-dimensional haystack: canonical correlation analysis for neuroscientists. Neuroimage 216 , 116745 (2020).
49. Smith, S. M. et al. A positive-negative mode of population covariation links brain connectivity, demographics and behavior. Nat. Neurosci. 18 , 1565 -1567 (2015).
50. Dadi, K. et al. Benchmarking functional connectome-based predictive models for resting-state fMRI. Neuroimage 192 , 115 -134 (2019).
51. Lake, E. M. R. et al. The functional brain organization of an individual allows prediction of measures of social abilities transdiagnostically in autism and attention-de /uniFB01 cit/hyperactivity disorder. Biol. Psychiatry 86 , 315 -326 (2019).
52. Cole, J. H. &amp; Franke, K. Predicting age using neuroimaging: innovative brain ageing biomarkers. Trends Neurosci. 40 , 681 -690 (2017).
53. Han, L. K. M. et al. Brain aging in major depressive disorder: results from the ENIGMA major depressive disorder working group. Mol. Psychiatry 26 , 5124 -5139 (2021).
54. Sturmfels, P. et al. A domain guided CNN architecture for predicting age from structural brain images. Preprint at arXiv https://doi.org/10.48550/arXiv.1808.04362 (2018).
55. Alfaro-Almagro, F. et al. Image processing and quality control for the /uniFB01 rst 10,000 brain imaging datasets from UK Biobank. Neuroimage 166 , 400 -424 (2018).
56. Donders, A. R. T., van der Heijden, G. J. M. G., Stijnen, T. &amp; Moons, K. G. M. Review: a gentle introduction to imputation of missing values. J. Clin. Epidemiol. 59 , 1087 -1091 (2006).
57. Madley-Dowd, P., Hughes, R., Tilling, K. &amp; Heron, J. The proportion of missing data should not be used to guide decisions on multiple imputation. J. Clin. Epidemiol. 110 , 63 -73 (2019).

## PROTOCOL

58. Burt, J. B., Helmer, M., Shinn, M., Anticevic, A. &amp; Murray, J. D. Generative modeling of brain maps with spatial autocorrelation. Neuroimage 220 , 117038 (2020).
59. Shinn, M. et al. Spatial and temporal autocorrelation weave human brain networks. Preprint at bioRxiv https://doi.org/10.1101/2021.06.01.446561 (2021).
60. Smith, S. M. &amp; Nichols, T. E. Threshold-free cluster enhancement: addressing problems of smoothing, threshold dependence and localisation in cluster inference. Neuroimage 44 , 83 -98 (2009).
61. Guo, C., Kang, J. &amp; Johnson, T. D. A spatial Bayesian latent factor model for image-on-image regression. Biometrics https://doi.org/10.1111/biom.13420 (2020).
62. Woolrich, M. W., Behrens, T. E. J. &amp; Smith, S. M. Constrained linear basis sets for HRF modelling using variational Bayes. Neuroimage 21 , 1748 -1761 (2004).
63. Liu, W., Zhu, P., Anderson, J. S., Yurgelun-Todd, D. &amp; Fletcher, P. T. Spatial regularization of functional connectivity using high-dimensional Markov random /uniFB01 elds. Med. Image Comput. Comput. Assist. Interv. 13 , 363 -370 (2010).
64. Song, H. F., Kennedy, H. &amp; Wang, X.-J. Spatial embedding of structural similarity in the cerebral cortex. Proc. Natl Acad. Sci. USA 111 , 16580 -16585 (2014).
65. Roberts, J. A. et al. The contribution of geometry to the human connectome. Neuroimage 124 , 379 -393 (2016).
66. Bijsterbosch, J. et al. Challenges and future directions for representations of functional brain organization. Nat. Neurosci. 23 , 1484 -1495 (2020).
67. Huertas, I. et al. A Bayesian spatial model for neuroimaging data based on biologically informed basis functions. Neuroimage 161 , 134 -148 (2017).

68.

Kia, S. M. &amp; Marquand, A. Normative modeling of neuroimaging data using scalable multi-task Gaussian processes. Preprint at

arXiv https://doi.org/10.48550/arXiv.1806.01047 (2018).

69. Kia, S. M., Beckmann, C. F. &amp; Marquand, A. F. Scalable multi-task Gaussian process tensor regression for normative modeling of structured variation in neuroimaging data. Preprint at arXiv https://doi.org/ 10.48550/arXiv.1808.00036 (2018).
70. Jahn, A. et al. Andy ' s Brain Book https://andysbrainbook.readthedocs.io/en/latest (2020).
71. Casey, B. J. et al. The Adolescent Brain Cognitive Development (ABCD) study: imaging acquisition across 21 sites. Dev. Cogn. Neurosci. 32 , 43 -54 (2018).
72. Thompson, P. M. et al. ENIGMA and global neuroscience: a decade of large-scale studies of the brain in health and disease across more than 40 countries. Transl. Psychiatry 10 , 100 (2020).
73. Beer, J. C. et al. Longitudinal ComBat: a method for harmonizing longitudinal multi-scanner imaging data. Neuroimage 220 , 117129 (2020).
74. Fortin, J.-P. et al. Harmonization of multi-site diffusion tensor imaging data. Neuroimage 161 , 149 -170 (2017).
75. Fortin, J.-P. et al. Harmonization of cortical thickness measurements across scanners and sites. Neuroimage 167 , 104 -120 (2018).
76. Johnson, W. E., Li, C. &amp; Rabinovic, A. Adjusting batch effects in microarray expression data using empirical Bayes methods. Biostatistics 8 , 118 -127 (2007).
77. Nygaard, V., Rødland, E. A. &amp; Hovig, E. Methods that remove batch effects while retaining group differences may lead to exaggerated con /uniFB01 dence in downstream analyses. Biostatistics 17 , 29 -39 (2016).
78. Noirhomme, Q. et al. Biased binomial assessment of cross-validated estimation of classi /uniFB01 cation accuracies illustrated in diagnosis predictions. Neuroimage Clin. 4 , 687 -694 (2014).
79. Marquand, A. F., Wolfers, T., Mennes, M., Buitelaar, J. &amp; Beckmann, C. F. Beyond lumping and splitting: a review of computational approaches for stratifying psychiatric disorders. Biol. Psychiatry Cogn. Neurosci. Neuroimaging 1 , 433 -447 (2016).
80. Rahimi, A. &amp; Recht, B. Random features for large-scale kernel machines. In NIPS'07: Proceedings of the 20th International Conference on Neural Information Processing Systems 1177 -1184 (2007).
81. Lv, J. et al. Individual deviations from normative models of brain structure in a large cross-sectional schizophrenia cohort. Mol. Psychiatry 26 , 3512 -3523 (2021).
82. Snelson, E., Ghahramani, Z. &amp; Rasmussen, C. Warped Gaussian processes. in Advances in Neural Information Processing Systems vol. 16 (MIT Press, 2004).
83. Hensman, J., Fusi, N. &amp; Lawrence, N. D. Gaussian processes for big data. Preprint at arXiv https://doi.org/10. 48550/arXiv.1309.6835 (2013).
84. Bethlehem, R. et al. Brain charts for the human lifespan. Nature https://doi.org/10.1038/s41586-022-04554-y (2022).
85. Rutherford, S. et al. Charting brain growth and aging at high spatial precision. eLife 11 , e72904 (2022).
86. Rutherford, S. et al. The Normative Modeling Framework for Computational Psychiatry (Zenodo, 2021); https://doi.org/10.5281/zenodo.5592153

## Acknowledgements

This research was supported by grants from the European Research Council (ERC, grant ' MENTALPRECISION ' 10100118 and ' BRAINMINT ' 802998), the Wellcome Trust under an Innovator award ( ' BRAINCHART ' , 215698/Z/19/Z) and a Strategic Award (098369/Z/12/Z), the Dutch Organisation for Scienti /uniFB01 c Research (VIDI grant 016.156.415). T.W. also gratefully acknowledges the Niels Stensen Fellowship as well as the European Union ' s Horizon 2020 research and innovation programme under the Marie SklodowskaCurie Grant agreement no. 895011.

## Author contributions

Conceptualization: S.R., S.M.K., T.W., C.F., M.Z., R.D., P.B., A.W., S.V., H.G.R., C.F.B. and A.F.M. Methodology: S.R., S.M.K., T.W., C.F., M.Z., R.D. and A.F.M. Data curation: S.R. and A.F.M. Writing -original draft: S.R. Writing -reviewing and editing: S.R., S.M.K., T.W., C.F., M.Z., R.D., P.B., A.W., S.V., H.G.R., C.F.B. and A.F.M. Visualization: S.R. Supervision: H.R., C.F.B. and A.F.M. Funding acquisition: H.R., C.F.B. and A.F.M.

## Competing interests

C.F.B. is director and shareholder of SBGNeuro Ltd. H.G.R. received speaker ' s honorarium from Lundbeck and Janssen. The other authors report no con /uniFB02 icts of interest.

## Additional information

Supplementary information The online version contains supplementary material available at https://doi.org/10.1038/s41596-022-00696-5. Correspondence and requests for materials should be addressed to Saige Rutherford.

Peer review information Nature Protocols thanks Linden Parkes and the other, anonymous, reviewer(s) for their contribution to the peer review of this work.

Reprints and permissions information is available at www.nature.com/reprints.

Publisher ' s note Springer Nature remains neutral with regard to jurisdictional claims in published maps and institutional af /uniFB01 liations.

Received: 16 August 2021; Accepted: 17 March 2022; Published online: 1 June 2022

## Related links

## Key references using this protocol

Marquand, A. F. et al. Mol. Psychiatry 24 , 1415 -1424 (2019): https://doi.org/10.1038/s41380-019-0441-1 Zabihi, M. et al. Transl. Psychiatry 10 , 384 (2020): https://doi.org/10.1038/s41398-020-01057-0 Wolfers, T. JAMA Psychiatry 75 , 1146 -1155 (2018): https://doi.org/10.1001/jamapsychiatry.2018.2467

![Image](Rutherford2022b_artifacts/image_000009_aa23f6802034a9a20dd7eabfb116518c9ec939307bb2ec783cd779bc0b424a94.png)

Corresponding author(s):

Saige Rutherford

saige.rutherford@donders.ru.nl

Last updated by author(s):

Jan 21, 2022

## Reporting Summary

Nature Research wishes to improve the reproducibility of the work that we publish. This form provides structure for consistency and transparency in reporting. For further information on Nature Research policies, see our Editorial Policies and the Editorial Policy Checklist.

## Statistics

For all statistical analyses, confirm that the following items are present in the figure legend, table legend, main text, or Methods section.

n/a Confirmed

The exact sample size ( n ) for each experimental group/condition, given as a discrete number and unit of measurement

A statement on whether measurements were taken from distinct samples or whether the same sample was measured repeatedly

The statistical test(s) used AND whether they are one- or two-sided

Only common tests should be described solely by name; describe more complex techniques in the Methods section.

A description of all covariates tested

A description of any assumptions or corrections, such as tests of normality and adjustment for multiple comparisons

A full description of the statistical parameters including central tendency (e.g. means) or other basic estimates (e.g. regression coefficient) AND variation (e.g. standard deviation) or associated estimates of uncertainty (e.g. confidence intervals)

For null hypothesis testing, the test statistic (e.g. F , t , r ) with confidence intervals, effect sizes, degrees of freedom and P value noted Give P values as exact values whenever suitable.

For Bayesian analysis, information on the choice of priors and Markov chain Monte Carlo settings

For hierarchical and complex designs, identification of the appropriate level for tests and full reporting of outcomes

Estimates of effect sizes (e.g. Cohen's d , Pearson's r ), indicating how they were calculated

Our web collection on statistics for biologists contains articles on many of the points above.

## Software and code

Policy information about availability of computer code

Data collection

All code in available on GitHub (https://github.com/predictive-clinical-neuroscience/PCNtoolkit-demo) and Zenodo (https://zenodo.org/ record/5592153#.YenuRFjMJb8)

Data analysis

All analysis was run using python version 3.8 and the predictive clinical neuroscience python package version 0.20.

For manuscripts utilizing custom algorithms or software that are central to the research but not yet described in published literature, software must be made available to editors and reviewers. We strongly encourage code deposition in a community repository (e.g. GitHub). See the Nature Research guidelines for submitting code &amp; software for further information.

## Data

Policy information about availability of data

All manuscripts must include a data availability statement. This statement should provide the following information, where applicable:

- Accession codes, unique identifiers, or web links for publicly available datasets
- A list of figures that have associated raw data
- A description of any restrictions on data availability

All data in available on GitHub (https://github.com/predictive-clinical-neuroscience/PCNtoolkit-demo) and Zenodo (https://zenodo.org/ record/5592153#.YenuRFjMJb8)

1

## Field-specific reporting

Please select the one below that is the best fit for your research. If you are not sure, read the appropriate sections before making your selection.

Life sciences

Behavioural &amp; social sciences

Ecological, evolutionary &amp; environmental sciences

For a reference copy of the document with all sections, see nature.com/documents/nr-reporting-summary-flat.pdf

## Behavioural &amp; social sciences study design

All studies must disclose on these points even when the disclosure is negative.

Study description

Cross-sectional samples, no repeated scans were included.

Research sample

Dataset includes a multi-site dataset combining publicly available data from the Human Connectome Project Young Adult, CAMCAN, and IXI samples.

Sampling strategy

N/A. All data used was secondary data analysis from public open data sets.

Data collection

N/A. All data used was secondary data analysis from public open data sets.

Timing

N/A

Data exclusions

Subjects had to have T1-weighted structural image that successfully finished Freesurfer surface reconstruction, and available demographic variables (age and sex).

Non-participation

N/A

Randomization

N/A

## Reporting for specific materials, systems and methods

We require information from authors about some types of materials, experimental systems and methods used in many studies. Here, indicate whether each material, system or method listed is relevant to your study. If you are not sure if a list item applies to your research, read the appropriate section before selecting a response.

## Materials &amp; experimental systems

n/a

Involved in the study

Antibodies

Eukaryotic cell lines

Palaeontology and archaeology

Animals and other organisms

Human research participants

Clinical data

Dual use research of concern

Methods

n/a

Involved in the study

ChIP-seq

Flow cytometry

MRI-based neuroimaging

## Human research participants

Policy information about studies involving human research participants

Population characteristics

Healthy subjects with no clinical diagnosis.

Recruitment

N/A. All data used was secondary data analysis from public open data sets.

Ethics oversight

Radboud University ethics board approved of this secondary data analysis study. All original data collection was approved by the ethics committee of the university where the original study was conducted.

Note that full information on the approval of the study protocol must also be provided in the manuscript.

2

## Magnetic resonance imaging

![Image](Rutherford2022b_artifacts/image_000010_ecf06c8d4f8a80c7056905f68e748f56367e99a1bfd4d5b0edb58748d53936e6.png)

## Experimental design

Design type

N/A

Design specifications

N/A

Behavioral performance measures

N/A

## Acquisition

Imaging type(s)

Structural

Field strength

3.0T

Sequence &amp; imaging parameters

High resolution T1-weighted MPRAGE images were used.

Area of acquisition

whole brain

Diffusion MRI

Used

Not used

## Preprocessing

Preprocessing software

Freesurfer version 6.0

Normalization

recon-all

Normalization template

fsaverage template, Desikan-Killian atlas.

Noise and artifact removal

Data that did not successfully run Freesurfer surface reconstruction were excluded. Euler number, a scan quality metric, was used to exclude low-quality subjects.

Volume censoring

N/A

## Statistical modeling &amp; inference

Model type and settings

Normative modeling using Bayesian linear regression to predict brain region Y, a n\_subjects x 1 vector from X, a matrix with n\_subjects x n\_covariates (i.e., age, sex, site, data quality metric). There is a separate regression model for each brain region.

Effect(s) tested

The individual-level outputs of normative modeling are the predicted brain score, the predictive variance (separated into modeling and noise components), a deviation score (Z-score, how much each subject deviate from the normative range). The overall performance is evaluated out-of-sample by correlating predicted and true values, calculating explained variance, standardized mean squared error, and mean standardized log loss.

Specify type of analysis:

Whole brain

ROI-based

Both

Anatomical location(s)

Desikan-Killian atlas was used.

Statistic type for inference (See Eklund et al. 2016)

ROI-level inference

Correction

N/A

## Models &amp; analysis

n/a

Involved in the study

Functional and/or effective connectivity

Graph analysis

Multivariate modeling or predictive analysis

Multivariate modeling and predictive analysis

Normative modeling using Bayesian Linear Regression.