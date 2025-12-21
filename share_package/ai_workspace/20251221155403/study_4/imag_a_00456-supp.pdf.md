## Supplementary Materials

![Image](./imag_a_00456-supp_artifacts/image_000000_ab62dc295b2786bb19f433ca6a124a82f495ca85706e043a0ed067c67fe956b5.png)

Figure S1: (a) Distribution of participants across the lifespan in the ABRIM dataset and (b) the PPP dataset.

![Image](./imag_a_00456-supp_artifacts/image_000001_0773425c3d59e77b4123d580b60db15b9d06647b81f4f5de31625cd62dcefadc.png)

## Data Curation

Figure S2: (a) Screenshot of the slice report of the R 2 * maps HTML generated for the whole ABRIM study. The command on the top of the figure automatically generates an HTML report with all the R2starmap NIFTI files organised in a BIDS folder. The main study HTML page shows one subject per row, with 3 sagittal, 3 coronal and 3 axial slices at the centre of the brain using a predefined intensity range (5-50 s -1 in the case above). If the rater clicks on one of the images, he/she will automatically be directed to a subject-specific HTML page where a larger number of slices can be rapidly viewed. (b) shows the subject-specific R 2 * map where a  larger  number  of  slices  are  shown.  Additionally,  it  is  possible  to  directly  navigate  to  the subject-specific 𝜒 report (c), to quickly check if artefacts are also present on the corresponding susceptibility map.

![Image](./imag_a_00456-supp_artifacts/image_000002_0a1e7b1e80e972f78445c3d666c6266656343af123c63abf2c9b832ba0c8c1d7.png)

Figure S3: (left) Group-averaged hybrid image derived using the results of each registration step and (right) the corresponding coefficient of variation (CoV) image across all subjects. The result  of  Step  1  shows  that  the  contrast  between  the  globus  pallidus  and  putamen  is  low without matching the contrast as in Step 2, resulting in blurry edges between the two close proximal structures. Note the boundary between the caudate nucleus and ventricle is notably sharper in the hybrid image of Step 3 compared to Step 2 (orange arrows). The CoV images also show lower values in the caudate nucleus/ventricle boundary at Step 3 (green arrows).

![Image](./imag_a_00456-supp_artifacts/image_000003_981bbc67a2ee1bc264bfcf74eba76cdc2523659225247d7f20a7ba0b9418def1.png)

Figure S4: Examples of subcortical grey matter parcellation on 4 subjects of different ages. The MuSus-100 labels are overlaid (coloured outlines) on the subject hybrid image registered to the common study space using (left) nonlinear transformation and (right) affine transformation only.

![Image](./imag_a_00456-supp_artifacts/image_000004_fd14b24dffa11b9dbaa6dcfee3cd759be726cf13785bb4d609b7e1718a39df5c.png)

## Comparison of conventional MP2RAGE lookup table T 1 mapping and MP2RAGE dictionary matching

The conventional MP2RAGE T 1 mapping strategy is based on the use of a lookup table that relates the MP2RAGE combined image (which has a dynamic range from -0.5 to 0.5) and a T 1 value  (Marques  et  al.,  2010).  While  this  approach  is  very  time-efficient  and  even straightforward to combine with additional transmit B 1 maps to reduce residual B 1 sensitivity (Marques and Gruetter, 2013) it has some caveats. Most noticeably, the ability to map T 1 values is only guaranteed in a given range of T 1 s where it is assumed that there is a nonbijective relationship between the combined MP2RAGE value and a given T 1 . This range is controlled by the inversion times, their flip angles and TRs. As normally the T 1 of CSF is of limited interest, for the sake of efficiency, the range in which T 1 is correctly measured is limited to the range of T 1 s of brain tissues. Although not reported in the original manuscript, it is not surprising that also an M 0 can be derived from the MP2RAGE sequence by simply dividing the signal  intensity  measured  at  the  second  inversion  time,  by  the  expected  signal  given  the computed T 1 . An alternative way to perform T 1 and M 0 mapping is fitting the 2 data points by the signal model (a two-variable fit to a two-point measurement). A computationally simple form to operationalise this is via a dot product between the measured signal (once the polarity has  been  recovered)  and  a  dictionary  of  signal  intensity  based  on  forward  model  signal simulations.

Both methods described above can account for transmit B 1 inhomogeneity as independently mapped (see https://github.com/Donders-Institute/MP2RAGE-related-scripts T1B1correctpackageTFL.m and  MP2RAGE\_dictionaryMatching.m ). Below is an example based on a random/representative selected subject from the ABRIM study is given (Jansen et al.,  2024)  with  the  following  sequence  parameters  for  mapping  purposes  (TR/TI 1 /TI 2 = 6000/700/2400 ms, 𝛼 1 = 𝛼 2 =6°, TR FLASH =6 ms, N shots per readout = 176). The figure below shows the derived M 0 and T 1 maps of each of the methods. It is clear when observing the M 0 maps using the conventional MP2RAGE map (Fig. S6d) that the CSF value (which is close to 100% water) is underestimated and lower than what is estimated for white matter and grey matter, whereas it is not the case in the dictionary matching.

Using FSL's BET (Jenkinson et al., 2012), the brain was extracted using the second inversion time image. The dictionary matching of M 0 and  T 1 values  was used the empirically define regions of high SNR outside the brain (M 0 &gt;2000, blue in segmented insert of Figure S6f) and define CSF regions inside the brain (T 1 &gt;3200 ms, green). The CSF mask was then dilated (inside  the  BET-derived  brain  mask)  using  Matlab's  function imdilate with  a  3x3x3  sphere structure  element  to  define  regions  likely  to  suffer  from  partial  volume  artefacts  (orange). Finally, the regions inside the brain that were neither CSF nor in the interface ROI were defined as brain tissue (dark red).

When carefully analysing the scatter plot in Figure S6f, it is clear that the estimated brain tissue values are unchanged, but that dictionary matching seems to be able to better compute T 1 in the CSF area (where the T 1 is known to be approximately ~4000 ms) and in regions of affected by the partial volume effect. Outside the brain, the main difference arises in fat regions with very low T 1 values where the measurement also clearly fails in the shown example (see Figure S6b,d).

For  the  current  study,  the  choice  of  the  T 1 mapping  approach  will  only  result  in  minor differences, as most of the structures analysed do not share a boundary with CSF (except Caudate and Thalamus). The choice of the approach might be more relevant when studying cortical grey matter (Chen et al., 2024).

Figure S5: M 0 and T 1 maps derived by the dictionary matching (fingerprinting) strategy (a, c) and the conventional MP2RAGE look-up table (b, d), respectively. Panels (e) and (f) show the scatter plots of the derived M 0 and T 1 values respectively using the look-up table vs dictionary matching. Colours in scatter plots correspond to the classes shown in the inset segmentation. Panel (g) shows the lookup table of the ABRIM protocol for different B 1 fields.

![Image](./imag_a_00456-supp_artifacts/image_000005_86f4e13345bc923f177ded43c58d3c3ee5ebf58b725f51828fa80ac1dc610789.png)

Figure  S6:  Normative  trajectories  derived  using  the  AHEAD  atlas  (Alkemade  et  al.,  2020) following the same process as described in the Methods section. The basal ganglia labels were obtained by hard thresholding the probabilistic images at 75%. The trajectories of the same structure derived using the MuSus-100 atlas are displayed alongside the AHEAD atlas results for direct comparison.

![Image](./imag_a_00456-supp_artifacts/image_000006_bf9983326bdf17a97f53b7f98f453a78ef08bc41eb18bd7e4b8038a4d63f525f.png)

![Image](./imag_a_00456-supp_artifacts/image_000007_0e4a2006627384e59a41def23ee3cf60ee13143991aefcb2d95a004967eb00dd.png)

Age

(years)

Figure S7: Normative trajectories of the interquartile range (IQR) of R 1 , R 2 *, 𝜒 as a function of age for 11 different deep grey matter structures present in the MuSus-100 atlas. The data points shown on the scatter plot are corrected for sex and hemispheric effects.

![Image](./imag_a_00456-supp_artifacts/image_000008_206850c5fda3fb74b0ece24dd26519a44a7992deb308dd5cf69ec07aae9a5b0b.png)

Age

(years)

Figure S8: Normative trajectories of the skewness of the distribution of R 1 , R 2 *, 𝜒 as a function of age for 11 different deep grey matter structures present in the MuSus-100 atlas. The data points shown on the scatter plot are corrected for sex and hemispheric effects.

Figure S9: R 2 * and 𝜒 maps in the subcortical regions of two Parkinson's disease patients with the highest z-scores in the putamen (based on ABRIM normative data). Red arrows indicate the high R 2 * and 𝜒 values in the putamen in contrast to the ABRIM group averaged maps. Because these offsets are observed on both quantitative maps, an artefactual origin can be readily discarded.

![Image](./imag_a_00456-supp_artifacts/image_000009_8cb21139b97044c1176ae77c7439a83b827d08547ce049e8732c215c24912a7f.png)

## Reference

- Alkemade, A., Mulder, M.J., Groot, J.M., Isaacs, B.R., Berendonk, N. van, Lute, N., Isherwood, S.J., Bazin, P.-L., Forstmann, B.U., 2020. The Amsterdam Ultra-high field adult lifespan database (AHEAD): A freely available multimodal 7 Tesla submillimeter magnetic resonance imaging database. Neuroimage 221, 117200. https://doi.org/10.1016/j.neuroimage.2020.117200
- Chen, X., Lu, P.-J., Ocampo-Pineda, M., Weigel, M., Chan, K.-S., Cagol, A., Zwiers, M., Jansen, M.G., Norris, D.G., Schädelin, S., Barakovic, M., Kuhle, J., Kappos, L., MelieGarcia, L., Granziera, C., Marques, J.P., 2024. Normative trajectories of R1, R2*, and Susceptibility values of the healthy human brain cortex, in: Proceedings 32. Annual Meeting International Society for Magnetic Resonance in Medicine. Singapore, p. 0576.
- Jansen, M.G., Zwiers, M.P., Marques, J.P., Chan, K.-S., Amelink, J.S., Altgassen, M., Oosterman, J.M., Norris, D.G., 2024. The Advanced BRain Imaging on ageing and Memory (ABRIM) data collection: Study design, data processing, and rationale. PLOS ONE 19, e0306006. https://doi.org/10.1371/journal.pone.0306006
- Jenkinson, M., Beckmann, C.F., Behrens, T.E.J., Woolrich, M.W., Smith, S.M., 2012. FSL. Neuroimage 62, 782-790. https://doi.org/10.1016/j.neuroimage.2011.09.015
- Marques, J.P., Gruetter, R., 2013. New Developments and Applications of the MP2RAGE Sequence - Focusing the Contrast and High Spatial Resolution R1 Mapping. PLoS ONE 8, e69294. https://doi.org/10.1371/journal.pone.0069294
