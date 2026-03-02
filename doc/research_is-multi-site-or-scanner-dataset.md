以下の脳MRIデータセットが、
multi site/scanner 由来のデータセットか否かの情報が欲しい。
カッコ内はそのデータセット使用している研究論文である。

判定基準:
  * 2施設以上、2スキャナ以上、同一施設で複数スキャナのいずれかを満たせば multi site/scanner 由来と判断する
参照ソース:
  * まずローカル文献を参照し、判断に迷う場合は公式データセットページ等を参照する
出力形式:
  * 各データセット毎にセクションを設けて Yes/No/Unknown と根拠を記載する

## ABIDE I
  * 参照論文: Bedford2025, Bethlehem2020, Chan2025A, Echave2024, Floris2021, Ge2024, Ilioska2024, Kim2023, Meijer2024, Pinaya2019, Segal2023, Segal2025, Shan2022, Wang2023, Yu2024, Zhang2023
  * 判定: Yes
  * 根拠: "Participants were drawn from ABIDE I and II across multiple sites." (`share_package/data/Bethlehem2020/DE_v10/md/DE_Bethlehem2020_by_codex_202510281235.md`)

## ABIDE II
  * 参照論文: Bedford2025, Bethlehem2020, Chan2025A, Echave2024, Floris2021, Ge2024, Ilioska2024, Kim2023, Meijer2024, Segal2023, Segal2025, Shan2022, Wang2023, Yu2024, Zhang2023
  * 判定: Yes
  * 根拠: "Participants were drawn from ABIDE I and II across multiple sites." (`share_package/data/Bethlehem2020/DE_v10/md/DE_Bethlehem2020_by_codex_202510281235.md`)

## ABRIM
  * 参照論文: Chan2025B
  * 判定: Unknown
  * 根拠: "data acquisition was performed at 3T (Siemens, Erlangen, Germany)" とあり、複数サイト/スキャナの明示がない。(`share_package/data/Chan2025B/materials/optimized/Chan2025B.pdf.md`)

## CNP
  * 参照論文: Rutherford2022, Rutherford2023
  * 判定: Unknown
  * 根拠: CNP は UCLA Consortium for Neuropsychiatric Phenomics 由来と記述されるが、複数サイト/スキャナの明示がない。(`share_package/data/Fang2024/materials/optimized/Fang2024.pdf.md`)

## REST-meta-MDD
  * 参照論文: Fang2025, Han2024B, Shao2024, Sun2025, Wu2023, Wu2024
  * 判定: Yes
  * 根拠: "large multi-site dataset of resting-state MRI" (`share_package/data/Fang2025/materials/optimized/Fang2025.pdf.md`)

## LEAP
  * 参照論文: Floris2021, Floris2024, Ilioska2024, Laidi2022, Looden2022, Zabihi2019, Zabihi2020
  * 判定: Yes
  * 根拠: "largest European multicenter initiative" および "one of six collaborating sites" (`share_package/data/Floris2021/materials/optimized/Floris2021.pdf.md`)

## UK Biobank
  * 参照論文: Cirstian2024, Fraza2024, Ge2024, Georgiadis2024, Janahi2022, Kasper2024, Kia2022, Kim2023, Kobbersmed2025, Loreto2024, Pinaya2021, Rutherford2022, Rutherford2023, Savage2024, Verdi2023, Verdi2024, Vieira2025, VillalonReina2024
  * 判定: Yes 
  * 根拠: UK BiobankのMRIデータは、基本的に **4つの専用イメージング拠点（Imaging Assessment Centres）**で収集されている。マシンは同一でSiemens Skyra。

## SAED
  * 参照論文: Geng2025
  * 判定: Yes
  * 根拠: "MRI data ... collected at site 1 with a Philips Ingenia MRI scanner" と "site 2 with a Siemens Verio MRI scanner" (`share_package/data/Geng2025/materials/optimized/Geng2025.pdf.md`)

## HCP Lifspan
  * 参照論文: Cirstian2024, FukamiGartner2023, Ge2024, Huang2024, Kia2022, Lee2025, Mansour2025, Rutherford2022, Rutherford2023, Savage2024, Vieira2025, VillalonReina2024, Young2024, Yu2024
  * 判定: Yes
  * 根拠: "HCP AGE (4 sites)" と "HCP DEV (4 sites)" の記載があり複数サイト。(`share_package/data/Young2024/materials/optimized/1-s2.0-S2451902224002416-mmc1.pdf.md`)

## Cam-CAN
  * 参照論文: Echave2024, Fraza2024, Ge2024, Huo2024, Italinna2023, Janssen2024, Kia2022, Kim2023, Lawn2024, Lin2024, Little2024, Little2025, Loreto2024, Rutherford2022, Rutherford2023, VillalonReina2024
  * 判定: No
  * 根拠: "all resting-state functional images ... obtained at a single site ... using a 3T Siemens TIM Trio scanner" (`share_package/data/Huo2024/materials/optimized/Huo2024.pdf.md`)

## ABIDE
  * 参照論文: Bayer2022, Coupe2022, Jiang2024, Rutherford2022
  * 判定: Yes
  * 根拠: "Participants were drawn from ABIDE I and II across multiple sites." (`share_package/data/Bethlehem2020/DE_v10/md/DE_Bethlehem2020_by_codex_202510281235.md`)

## IBCDR
  * 参照論文: Jing2023, Lin2023
  * 判定: Yes
  * 根拠: "data were collected from multiple hospitals/institutions in China" (`share_package/data/Lin2023/materials/optimized/Lin2023.pdf.md`)

## ADNI
  * 参照論文: Coupe2022, Ge2024, Kim2023, Kumar2024, Kumar2025, Pinaya2021, Verdi2024, Young2024
  * 判定: Yes
  * 根拠: "*ADNI has multiple sites but considered as a single site in this analysis" (`share_package/data/Young2024/materials/optimized/1-s2.0-S2451902224002416-mmc1.pdf.md`)

## ENIGMA
  * 参照論文: Ge2024, Lamsma2024
  * 判定: Yes
  * 根拠: "collect ... data from different cohorts and different scanning sites" (`share_package/data/Bayer2022/materials/optimized/Bayer2022.pdf.md`)

## HCP-D
  * 参照論文: Cirstian2024, FukamiGartner2023, Ge2024, Huang2024, Kia2022, Lee2025, Mansour2025, Rutherford2022, Rutherford2023, Savage2024, VillalonReina2024, Young2024, Yu2024
  * 判定: Yes
  * 根拠: "HCP DEV (4 sites)" の記載で複数サイト。(`share_package/data/Young2024/materials/optimized/1-s2.0-S2451902224002416-mmc1.pdf.md`)

## NAKO
  * 参照論文: Leenings2024
  * 判定: Unknown
  * 根拠: ローカル文献内で複数サイト/複数スキャナの明示が見つからず、公式情報の確認が必要。

## Australian Schizophrenia Research Bank
  * 参照論文: Lv2021, Segal2023, Segal2025
  * 判定: Yes
  * 根拠: "Participants were recruited from five sites in Australia" (`share_package/data/Lv2021/materials/optimized/Lv2021.pdf.md`)

## GSP
  * 参照論文: Echave2024, Ge2024, Ma2024
  * 判定: Yes
  * 根拠: "GSP ... a large-scale, multi-site brain imaging study" (`share_package/data/Ma2024/materials/optimized/Ma2024.pdf.md`)

## ABCD
  * 参照論文: Ge2024, Kia2022, Loreto2024, Mendes2024, Rutherford2022, Rutherford2023, Verdi2023, Verdi2024, VillalonReina2024
  * 判定: Yes
  * 根拠: "This was a multisite, longitudinal study" (`share_package/data/Mendes2024/materials/optimized/Mendes2024.pdf.md`)

## PNC
  * 参照論文: Fraza2024, Jalbrzikowski2019, Kia2022, Kim2023, Loreto2024, Parkes2021, Rutherford2022, Rutherford2023
  * 判定: No
  * 根拠: "All scanning was done using the same sequences on the same scanner" (`share_package/data/Parkes2021/materials/optimized/Parkes2021.pdf.md`)

## OASIS
  * 参照論文: Coupe2022, Echave2024, Fraza2024, Janssen2024, Kim2023, Little2024, Little2025, Loreto2024, Pinaya2021, Romascano2024, Rutherford2022, Rutherford2023, Verdi2023, Verdi2024, VillalonReina2024
  * 判定: Yes
  * 根拠: "scans were acquired at two field strengths using Siemens MRI scanners: Magnetom Sonata and Avanto (1.5T) ... Biograph mMR and Magnetom Trio (3T)" (`share_package/data/Romascano2024/materials/optimized/Romascano2024.pdf.md`)

## HCP-YA
  * 参照論文: Cirstian2024, Ge2024, Huang2024, Kia2022, Little2024, Mansour2025, Rutherford2022, Rutherford2023, Sampaio2025, Savage2024, Vieira2025, VillalonReina2024, Young2024
  * 判定: No
  * 根拠: "HCP YA (WSU)" と単一サイトの記載で、複数サイト/スキャナの明示がない。(`share_package/data/Young2024/materials/optimized/1-s2.0-S2451902224002416-mmc1.pdf.md`)

## DIDA-MDD
  * 参照論文: Han2024B, Sun2023
  * 判定: Yes
  * 根拠: "1148 patients ... from 9 research centers from the DIDA-MDD" (`share_package/data/Sun2023/materials/optimized/Sun2023.pdf.md`)

## REST-meta-MDD
  * 参照論文: Fang2025, Han2024B, Shao2024, Sun2025, Wu2023, Wu2024
  * 判定: Yes
  * 根拠: "large multi-site dataset of resting-state MRI" (`share_package/data/Fang2025/materials/optimized/Fang2025.pdf.md`)

## TOP
  * 参照論文: Kim2023, Loreto2024, Rutherford2022, Rutherford2023, Segal2023, Segal2025, Wolfers2018, Wolfers2021
  * 判定: Unknown
  * 根拠: ローカル文献内で複数サイト/複数スキャナの明示が見つからず、公式情報の確認が必要。

## IMpACT
  * 参照論文: Segal2023, Segal2025, Wolfers2020
  * 判定: Yes
  * 根拠: "International Multi-centre persistent ADHD CollaboraTion" の名称に multi-centre が明示。(`share_package/data/Segal2025/materials/optimized/Segal2025.pdf.md`)

## devCCNP
  * 参照論文: Jia2024, Jia2025, Yang2025, Yu2024
  * 判定: Yes
  * 根拠: "Samples from the two different centers ... devCCNP" および "multisite sample" (`share_package/data/Yang2025/materials/optimized/Yang2025.pdf.md`)
