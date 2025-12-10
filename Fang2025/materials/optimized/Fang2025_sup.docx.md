**Supplementary information for individualized resting-state functional connectivity abnormalities unveil two major depressive disorder subtypes with contrasting abnormal patterns of abnormality**

**Supplementary methods**

**Cluster stability estimation**

To evaluate the stability of the clustering results, we implemented two validation approaches: subsample validation and leave-one-site-out validation. Specifically, we performed subsampling 100 times with random selections and applied a leave-one-site-out method to validate the clustering outcomes. For each clustering solution, we generated a binary co-occurrence matrix that indicated whether each pair of patients was assigned to the same cluster (value = 1) or different clusters (value = 0). We then computed a stability matrix of size nn (where n represents the number of patients) by averaging these pairwise co-occurrence matrices across the subsamples. The stability index was derived from the mean value of the upper triangular portion of the stability matrix. A higher stability index indicates more consistent clustering results. To assess the statistical significance of the observed stability index, we repeated the feature selection and clustering process on 100 random permutations of the original case/control labels, generating a null distribution of stability index values.

**Supplementary results**

To assess the sensitivity of clustering outcomes to parameter setting, we repeated the feature selection and clustering process using a consistency rate threshold of 90%. Of the 19,900 functional connections, 670 exhibited a consistency rate greater than 90%. We also conducted a significant cluster test and applied cluster ensemble voting (results are showed in Figure S3A). The analysis revealed two significant MDD subtypes, with 402 cases in subtype 1 and 874 in subtype 2. The ARI between these cluster outcomes using the originally reported outcomes was 0.97, indicating strong consistency. As with our initial results, these two subtypes exhibited contrasting patterns of functional connectivity abnormalities relative to HCs, though no significant differences were observed in demographic and clinical characteristics (uncorrected *p* &gt; 0.05). Abnormal patterns of functional connectivity were depicted in Figure 3B and Figure 3C. Additionally, patients in subtype 1 had a slightly longer illness duration compared to patients in subtype 2 (uncorrected *p* = 0.06, t = 1.88, df=983, cohen’ *d* = 0.13). The two identified subtypes significantly improved case-control classification accuracy (model of subtype 1 vs. model of all patients, t=185.29, degree of freedom(df)=198, cohen’s *d* = 26.20, Bonferroni corrected *p* &lt; 0.01; model of subtype 2 vs. model of all patients, t=88.73, df=198, cohen’s *d* = 12.55, Bonferroni corrected *p* &lt; 0.01). Furthermore, the classification accuracy between these two subtypes was 94.00%. Second, to further examine the influence of medication, we validated clustering outcomes in untreated patients. Untreated patients were divided into two subtypes (129 in subtype 1 and 318 in subtype 2) which also exhibited contrasting patterns of functional connectivity abnormalities relative to HCs (Figure S5, Table S2), with no significant differences in demographic and clinical characteristics, consistent with outcomes we reported. Notably, these untreated subtypes exhibited higher case-control classification accuracy than the full cohort. The classification accuracy of all patients, subtype 1, and subtype 2 were 63.01%, 83.05%, and 70.39%, respectively. These findings confirm that the identified subtypes significantly improved case-control classification accuracy. Lastly, to investigate whether the identified extreme functional connections were specific to MDD relative to other psychiatric disorders, we applied the identical analysis to schizophrenia patients from the COBRE dataset. Of the 19,900 functional connections, 1,292 exhibited a consistency rate greater than 95%. These extreme functional connections predominantly involved the DMN and the visual, sensorimotor and ventral attention networks (Figure 4). Abnormal functional connections using two sample t-tests, were most prominent within the visual network and between the visual and sensorimotor networks (Figure 4).

**Table S1** . Functional enrichment results

| Cognitive terms   |   Permutation  *p* |
|-------------------|--------------------|
| Motivation        |              0.027 |
| Pain              |              0.029 |
| Reward            |              0.02  |

**Table S2** . Demographic and clinical information of untreated MDD subtypes

| Characteristic                 | Subtype 1 (n=129)   | Subtype 2 (n=318)   | p     |
|--------------------------------|---------------------|---------------------|-------|
| Age, mean (SD), y              | 32.71(12.00)        | 32.73(11.97)        | 0.99a |
| Males, No. (%)                 | 76(58.91%)          | 203(63.84)          | 0.33b |
| Mean FD, mean (SD)             | 0.12(0.10)          | 0.12(0.08)          | 0.54a |
| HAMD, mean (SD)                | 21.55(5.43)         | 22.25(6.43)         | 0.33a |
| Illness duration, mean (SD), m | 23.71(48.34)        | 24.48(39.66)        | 0.87a |
| Age of onset, mean (SD), y     | 29.73(10.72)        | 30.69(11.68)        | 0.45a |
| First episode/recurrent        | 93/21               | 225/55              | 0.78b |

Note: Mean FD, mean frame-wise displacement; HAMD, Hamilton rating scale for depression; a two-tailed two sample *t* test; b Chi-square test.

**Figure S1** . Number of extreme functional connections in patients with MDD

![Image](./Fang2025_sup_artifacts/image_000000_ed1bb803afc268f4774b244901b0fc3f2023dd3389fcfe2cdc76a3381cb2bbb4.png)

**Figure S2** . Statistical significance of clustering using different features. (A). Extreme functional connections with the consistency rate exceeding 95%. (B). Abnormal functional connections using two sample t-tests. (C) Extreme functional connections with the consistency rate exceeding 90%. The dash-line indicates the cluster index derived from the k-means clustering, calculated as the ratio of the sum of within-cluster sums of squares to the total sum of squares around the overall mean. The P-value represents the proportion of simulated cluster indices that exceed the empirical P-value. P-vNorm refers to the P-value obtained from a Gaussian fit.

![Image](./Fang2025_sup_artifacts/image_000001_5a9b44b32ce9017e33ebb7b066d03f86a12f704d1e991ce409a0ec3b25e1b686.png)

**Figure S3** . Network- and node-level abnormalities of the identified MDD subtypes using different functional connectivity features. (A). The cluster ensemble voting technique identify the 2-cluster solution as the most optimal (indicated by the red asterisk) across the entire MDD sample, based on either T-test-based features (from a two-sample t-test) or tolerance interval features with a consistency rate of 90% (TI-based features). Network- and node-level abnormalities of the validated subtypes, identified using TI-based features with a 90% consistency rate, are shown in B (Subtype 1) and C (Subtype 2). Note: VN, visual network; SMN, sensorimotor network; DAN, dorsal attention network; VAN, ventral attention network; Lim, limbic network; FPN, frontoparietal network; DMN, default mode network.

![Image](./Fang2025_sup_artifacts/image_000002_e65cc2730449ca1029c93dbbae7c8e0ab734b12f7c5bcc1f5a4a18a91a85e0a6.png)

**Figure S4** . Classification accuracy and comparison of models using all patients and the identified subtypes based on features with a 90% consistency rate

![Image](./Fang2025_sup_artifacts/image_000003_80076d15da5b66045dde95e3e5e1e65c646487423ebb8894718605abb482fd59.png)

**Figure S5** . Network- and node-level abnormalities of the identified untreated subtypes are depicted in A (subtype 1) and B (subtype 2). Note, VN, visual network; SMN, sensorimotor network; DAN, dorsal attention network; VAN, ventral attention network; Lim, limbic network; FPN, frontoparietal network; DMN, default mode network.

