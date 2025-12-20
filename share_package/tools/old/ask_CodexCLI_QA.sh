#!/bin/bash


AuthorYearArray=()

# 上田担当分
#AuthorYearArray+=("Baldwin2022")
#AuthorYearArray+=("Bayer2022")
AuthorYearArray+=("Bedford2025")
#AuthorYearArray+=("Berthet2025")
#AuthorYearArray+=("Bethlehem2020")
#AuthorYearArray+=("Bethlehem2021")
#AuthorYearArray+=("Bhome2024")
#AuthorYearArray+=("CardenasDeLaParra2019")
#AuthorYearArray+=("Chan2025A" "Chan2025B" "Chien2022")
#AuthorYearArray+=("Cirstian2024")
#AuthorYearArray+=("Coupe2022")
#AuthorYearArray+=("DeMeo2019" "DiBiase2022")
#AuthorYearArray+=("Ebadi2024")

#AuthorYearArray+=("Echave2024")
#AuthorYearArray+=("Elad2021")
#AuthorYearArray+=("Fang2024" "Fang2025" "Feng2024")
#AuthorYearArray+=("Feng2025" "Floris2021" "Floris2024" "Fraza2023" "FukamiGartner2023")
#AuthorYearArray+=("GarciaSanMartin2025" "Ge2024")
#AuthorYearArray+=("Geng2025" "Georgiadis2024")

#AuthorYearArray+=("Giacomel2025")
#AuthorYearArray+=("Gimbel2025" "Gordaliza2024" "Haas2024" "Han2023" "Han2024A")

#AuthorYearArray+=("Han2024B" "Haukvik2025" "Holz2023" "Hua2025" "Huang2024")
#AuthorYearArray+=("Huo2024" "Ilioska2024" "Italinna2023" "Jalbrzikowski2019" "Janahi2022")

#AuthorYearArray+=("Janssen2021" "Janssen2024" "Ji2023" "Jia2024" "Jia2025")
#AuthorYearArray+=("Jiang2024" "Jing2023" "Joo2024" "Kasper2024" "Kia2022")

#AuthorYearArray+=("Kim2023" "Kim2024" "Kobbersmed2025" "Kumar2024" "Kumar2025")
#AuthorYearArray+=("Laidi2022" "Lamsma2024" "Lawn2024")
#AuthorYearArray+=("Lee2025" "Leenings2024")

#AuthorYearArray+=("Leiberg2023" "Lin2023" "Lin2024" "Little2024" "Little2025")
#AuthorYearArray+=("Liu2024" "Looden2022" "Loreto2024" "Lv2021" "Ma2024")

#AuthorYearArray+=("Mansour2025" "Mao2025" "Martin2025" "Meijer2024" "Mendes2024")
#AuthorYearArray+=("Narai2024")
#AuthorYearArray+=("OliveiraSaraiva2023" "Parkes2021" "Pinaya2019" "Pinaya2021")

#AuthorYearArray+=("RehakBuckova2025" "Remiszewski2022" "Romascano2024" "Rutherford2022")
#AuthorYearArray+=("Rutherford2023")
#AuthorYearArray+=("Sampaio2025" "Savage2024" "Segal2023" "Segal2025" "Shan2022")

#AuthorYearArray+=("Shao2024" "Sun2023" "Sun2025" "Tabbal2025" "Thukral2024")
#AuthorYearArray+=("Tong2024" "Verdi2023" "Verdi2024" "Vieira2025" "VillalonReina2024")

#AuthorYearArray+=("Wang2023" "Wen2025" "Wolfers2018" "Wolfers2020" "Wolfers2021")
#AuthorYearArray+=("Worker2023" "Wu2023" "Wu2024" "Xiao2024" "Yang2025")

#AuthorYearArray+=("Young2024" "Yu2024" "Zabihi2019" "Zabihi2020" "Zhang2022")
#AuthorYearArray+=("Zhang2023" "Zheng2024")


for AuthorYear in ${AuthorYearArray[@]}; do

  echo "===== Regarding ${AuthorYear} ====="
  
  cd ./${AuthorYear}

  # Create Output Folder
  if [[ ! -d ./QA/json ]]; then
      mkdir -p ./QA/json
  fi

  
  # Ask AI agent if the result json was not found.
  _ai_agent=codex
  _folder="./QA/json/"
  _trgt="QA_${AuthorYear}_by_${_ai_agent}_*.json"
  if find ${_folder} -maxdepth 1 -type f -iname ${_trgt} | grep -q .; then
      
      echo "The json file already exists. Skip asking."
  
  else
      # Make a flag file  
      echo "The json file is not created yet." > ../QA_${AuthorYear}_by_${_ai_agent}_is_not_yet.txt   
  
      # Ask AI Agent
      codex exec --full-auto --skip-git-repo-check -C . "QA_Guide_v6.md に従って作業をしてください。"
  
      
      # Check Result
      if find ${_folder} -maxdepth 1 -type f -iname ${_trgt} | grep -q .; then
          echo "The JSON was created."
          rm ../QA_${AuthorYear}_by_${_ai_agent}_is_not_yet.txt 
      fi
  
  fi

  cd ../
  echo ""
done
