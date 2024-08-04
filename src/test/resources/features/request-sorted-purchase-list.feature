Feature: Sorted Purchase List

  Scenario: Retrieve a list of purchases sorted by value asc - success
    Given the system contains clients with the following data
      | Name                              | Cpf          |
      | Geraldo Pedro Julio Nascimento    | 05870189179  |
      | Vitória Alícia Mendes             | 20623850567  |
      | Teresinha Daniela Galvão          | 04372012950  |
      | Gabriel Rafael Dias               | 85067950013  |
      | Andreia Emanuelly da Mata         | 27737287426  |
      | Natália Sandra da Cruz            | 03763001590  |
      | Catarina Sebastiana Analu Almeida | 88901767767  |
      | Hadassa Daniela Sales             | 1051252612   |
      | Kaique Danilo Alves               | 20634031392  |
      | Pietra Antônia Brenda Silva       | 74302668512  |
      | Maitê Kamilly Corte Real          | 022484638124 |
      | Isis Isis Ramos                   | 29457224965  |
      | Fabiana Melissa Nunes             | 824643755772 |
      | Ian Joaquim Giovanni Santos       | 96718391344  |
    And client cpf "05870189179" contains purchases with the following data
      | Product Code | Quantity |
      | 1            | 6        |
      | 15           | 4        |
      | 10           | 2        |
      | 5            | 3        |
      | 2            | 5        |
    And client cpf "20623850567" contains purchases with the following data
      | Product Code | Quantity |
      | 1            | 8        |
    And client cpf "04372012950" contains purchases with the following data
      | Product Code | Quantity |
      | 14           | 3        |
      | 20           | 3        |
      | 4            | 2        |
    And client cpf "85067950013" contains purchases with the following data
      | Product Code | Quantity |
      | 17           | 6        |
      | 19           | 4        |
    And client cpf "27737287426" contains purchases with the following data
      | Product Code | Quantity |
      | 5            | 6        |
      | 4            | 4        |
      | 3            | 2        |
      | 17           | 3        |
      | 13           | 5        |
      | 14           | 5        |
    And client cpf "03763001590" contains purchases with the following data
      | Product Code | Quantity |
      | 6            | 6        |
      | 4            | 4        |
    And client cpf "88901767767" contains purchases with the following data
      | Product Code | Quantity |
      | 16           | 6        |
      | 2            | 4        |
    And client cpf "1051252612" contains purchases with the following data
      | Product Code | Quantity |
      | 19           | 3        |
      | 17           | 3        |
      | 12           | 2        |
    And client cpf "20634031392" contains purchases with the following data
      | Product Code | Quantity |
      | 8            | 3        |
    And client cpf "74302668512" contains purchases with the following data
      | Product Code | Quantity |
      | 3            | 3        |
    And client cpf "022484638124" contains purchases with the following data
      | Product Code | Quantity |
      | 19           | 6        |
      | 15           | 4        |
    And client cpf "29457224965" contains purchases with the following data
      | Product Code | Quantity |
      | 18           | 6        |
      | 1            | 4        |
    And client cpf "824643755772" contains purchases with the following data
      | Product Code | Quantity |
      | 18           | 2        |
      | 10           | 10       |
    And client cpf "96718391344" contains purchases with the following data
      | Product Code | Quantity |
      | 15           | 6        |
      | 14           | 4        |
      | 3            | 20       |
      | 17           | 13       |
      | 2            | 15       |
    And the system contains products with the following data
      | Code | Wine Type  | Price  | Vintage | Purchase Year |
      | 1    | Tinto      | 229.99 | 2017    | 2018          |
      | 2    | Branco     | 126.50 | 2018    | 2019          |
      | 3    | Rosé       | 121.75 | 2019    | 2020          |
      | 4    | Espumante  | 134.25 | 2020    | 2021          |
      | 5    | Chardonnay | 128.99 | 2021    | 2022          |
      | 6    | Tinto      | 327.50 | 2016    | 2017          |
      | 7    | Branco     | 125.25 | 2017    | 2018          |
      | 8    | Rosé       | 120.99 | 2018    | 2019          |
      | 9    | Espumante  | 135.50 | 2019    | 2020          |
      | 10   | Chardonnay | 130.75 | 2020    | 2021          |
      | 11   | Tinto      | 128.99 | 2017    | 2018          |
      | 12   | Branco     | 106.50 | 2018    | 2019          |
      | 13   | Rosé       | 121.75 | 2019    | 2020          |
      | 14   | Espumante  | 134.25 | 2020    | 2021          |
      | 15   | Chardonnay | 188.99 | 2021    | 2022          |
      | 16   | Tinto      | 127.50 | 2016    | 2017          |
      | 17   | Branco     | 125.25 | 2017    | 2018          |
      | 18   | Rosé       | 120.99 | 2018    | 2019          |
      | 19   | Espumante  | 135.50 | 2019    | 2020          |
      | 20   | Chardonnay | 130.75 | 2020    | 2021          |
    When I request the list of purchases
    Then should receive the list of purchases sorted in ascending order by value
      | Customer Name                     | Customer Cpf | Product Code | Product Wine Type | Product Price | Product Vintage | Product Purchase Year | Quantity | Total Value |
      | Hadassa Daniela Sales             | 1051252612   | 12           | Branco            | 106.50        | 2018            | 2019                  | 2        | 213.00      |
      | Fabiana Melissa Nunes             | 824643755772 | 18           | Rosé              | 120.99        | 2018            | 2019                  | 2        | 241.98      |
      | Andreia Emanuelly da Mata         | 27737287426  | 3            | Rosé              | 121.75        | 2019            | 2020                  | 2        | 243.50      |
      | Geraldo Pedro Julio Nascimento    | 05870189179  | 10           | Chardonnay        | 130.75        | 2020            | 2021                  | 2        | 261.50      |
      | Teresinha Daniela Galvão          | 04372012950  | 4            | Espumante         | 134.25        | 2020            | 2021                  | 2        | 268.50      |
      | Kaique Danilo Alves               | 20634031392  | 8            | Rosé              | 120.99        | 2018            | 2019                  | 3        | 362.97      |
      | Pietra Antônia Brenda Silva       | 74302668512  | 3            | Rosé              | 121.75        | 2019            | 2020                  | 3        | 365.25      |
      | Andreia Emanuelly da Mata         | 27737287426  | 17           | Branco            | 125.25        | 2017            | 2018                  | 3        | 375.75      |
      | Hadassa Daniela Sales             | 1051252612   | 17           | Branco            | 125.25        | 2017            | 2018                  | 3        | 375.75      |
      | Geraldo Pedro Julio Nascimento    | 05870189179  | 5            | Chardonnay        | 128.99        | 2021            | 2022                  | 3        | 386.97      |
      | Teresinha Daniela Galvão          | 04372012950  | 20           | Chardonnay        | 130.75        | 2020            | 2021                  | 3        | 392.25      |
      | Teresinha Daniela Galvão          | 04372012950  | 14           | Espumante         | 134.25        | 2020            | 2021                  | 3        | 402.75      |
      | Hadassa Daniela Sales             | 1051252612   | 19           | Espumante         | 135.50        | 2019            | 2020                  | 3        | 406.50      |
      | Catarina Sebastiana Analu Almeida | 88901767767  | 2            | Branco            | 126.50        | 2018            | 2019                  | 4        | 506.00      |
      | Andreia Emanuelly da Mata         | 27737287426  | 4            | Espumante         | 134.25        | 2020            | 2021                  | 4        | 537.00      |
      | Natália Sandra da Cruz            | 03763001590  | 4            | Espumante         | 134.25        | 2020            | 2021                  | 4        | 537.00      |
      | Ian Joaquim Giovanni Santos       | 96718391344  | 14           | Espumante         | 134.25        | 2020            | 2021                  | 4        | 537.00      |
      | Gabriel Rafael Dias               | 85067950013  | 19           | Espumante         | 135.50        | 2019            | 2020                  | 4        | 542.00      |
      | Andreia Emanuelly da Mata         | 27737287426  | 13           | Rosé              | 121.75        | 2019            | 2020                  | 5        | 608.75      |
      | Geraldo Pedro Julio Nascimento    | 05870189179  | 2            | Branco            | 126.50        | 2018            | 2019                  | 5        | 632.50      |
      | Andreia Emanuelly da Mata         | 27737287426  | 14           | Espumante         | 134.25        | 2020            | 2021                  | 5        | 671.25      |
      | Isis Isis Ramos                   | 29457224965  | 18           | Rosé              | 120.99        | 2018            | 2019                  | 6        | 725.94      |
      | Gabriel Rafael Dias               | 85067950013  | 17           | Branco            | 125.25        | 2017            | 2018                  | 6        | 751.50      |
      | Geraldo Pedro Julio Nascimento    | 05870189179  | 15           | Chardonnay        | 188.99        | 2021            | 2022                  | 4        | 755.96      |
      | Maitê Kamilly Corte Real          | 022484638124 | 15           | Chardonnay        | 188.99        | 2021            | 2022                  | 4        | 755.96      |
      | Catarina Sebastiana Analu Almeida | 88901767767  | 16           | Tinto             | 127.50        | 2016            | 2017                  | 6        | 765.00      |
      | Andreia Emanuelly da Mata         | 27737287426  | 5            | Chardonnay        | 128.99        | 2021            | 2022                  | 6        | 773.94      |
      | Maitê Kamilly Corte Real          | 022484638124 | 19           | Espumante         | 135.50        | 2019            | 2020                  | 6        | 813.00      |
      | Isis Isis Ramos                   | 29457224965  | 1            | Tinto             | 229.99        | 2017            | 2018                  | 4        | 919.96      |
      | Ian Joaquim Giovanni Santos       | 96718391344  | 15           | Chardonnay        | 188.99        | 2021            | 2022                  | 6        | 1133.94     |
      | Fabiana Melissa Nunes             | 824643755772 | 10           | Chardonnay        | 130.75        | 2020            | 2021                  | 10       | 1307.50     |
      | Geraldo Pedro Julio Nascimento    | 05870189179  | 1            | Tinto             | 229.99        | 2017            | 2018                  | 6        | 1379.94     |
      | Ian Joaquim Giovanni Santos       | 96718391344  | 17           | Branco            | 125.25        | 2017            | 2018                  | 13       | 1628.25     |
      | Vitória Alícia Mendes             | 20623850567  | 1            | Tinto             | 229.99        | 2017            | 2018                  | 8        | 1839.92     |
      | Ian Joaquim Giovanni Santos       | 96718391344  | 2            | Branco            | 126.50        | 2018            | 2019                  | 15       | 1897.50     |
      | Natália Sandra da Cruz            | 03763001590  | 6            | Tinto             | 327.50        | 2016            | 2017                  | 6        | 1965.00     |
      | Ian Joaquim Giovanni Santos       | 96718391344  | 3            | Rosé              | 121.75        | 2019            | 2020                  | 20       | 2435.00     |
