# Sudoku
### Sudoku with Simulated Annealing algoritm

![Demo](https://media2.giphy.com/media/v1.Y2lkPTc5MGI3NjExdjI4dDVpOTR5eWg0bnNna3cwdTE4aGVoZ3Y0amEwdmZwamI0enpsayZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/vW4YOXlkO55sJW3W19/giphy.gif)

### 1. Uzdevuma formulējums:

Uzdevums ir atrisināt doto Sudoku uzdevumu, izmantojot optimizācijas algoritmu Simulated Annealing. Mērķis ir aizpildīt tukšās rūtiņas daļēji aizpildītā Sudoku režģī, ievērojot Sudoku noteikumus: katram skaitlim no 1 līdz 9 jāparādās tieši vienu reizi katrā rindā, kolonnā un 3x3 apakšrežģī. Algoritma galvenais uzdevums ir atrast risinājumu, kurā netiek pārkāpti noteikumi, līdz ar to tiek iegūts derīgs Sudoku risinājums.


### 2. Algoritma apraksts:

Domēns: ir 9x9 Sudoku režģis, kurā dažas rūtiņas ir iepriekš aizpildītas ar skaitļiem, bet atlikušās rūtiņas (ar vērtību nulle) jāaizpilda ar derīgiem skaitļiem, ievērojot Sudoku noteikumus.

Novērtēšana: risinājuma kvalitāte tiek novērtēta, izmantojot sodu punktus. Par katru skaitļa dublikātu rindā, kolonnā vai blokā tiek pievienots sods, kā arī par iztrūkstošiem skaitļiem. Algoritma mērķis ir minimizēt sodu skaitu līdz nullei, kas norāda uz derīgu risinājumu.

Gājiens: Simulated Annealing sākas ar sākotnējo režģi, kurā tukšās rūtiņas tiek aizpildītas ar nejaušiem skaitļiem no 1 līdz 9, kas trūkst katrā blokā (3x3 apakšrežģī). Algoritms iteratīvi uzlabo risinājumu, veicot izmaiņas, nejauši izvēloties divas nefiksētas rūtiņas vienā blokā un samainot to vērtības. Pēc katras izmaiņas novērtējot jauno režģi pēc sodu punktiem. Izvēloties, vai pieņemt vai noraidīt jauno konfigurāciju, pamatojoties uz to, vai rezultāts uzlabojas, vai arī, ja tas neuzlabojas, izmantojot varbūtības pieņemšanas kritēriju, ko kontrolē temperatūra T, kas samazinās laika gaitā. Ja tiek atrasts risinājums ar nulles sodu punktiem, algoritms beidzas. Ja uzlabojumi ilgāku laiku netiek veikti, režģis tiek no jauna randomizēts, lai izvairītos no iestrēgšanas.

### 3. Testēšanas apraksts:

Algoritms tika pārbaudīts, izmantojot vairākus dažādas sarežģītības Sudoku uzdevumus. Testi ietvēra vieglu uzdevumu, vidējas sarežģītības uzdevumu un grūtu uzdevumu. Katra testa laikā tika reģistrēts izpildes laiks un gala sodu punkti, lai novērtētu algoritma veiktspēju uz dažādiem uzdevumiem. Rezultāti parādīja, ka Simulated Annealing efektīvi darbojas vienkāršākos uzdevumos, bet sarežģītākos uzdevumos nepieciešama papildu optimizācija vai algoritma parametru (piemēram, dzesēšanas ātruma) pielāgošana.
