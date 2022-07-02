<?php
include 'header.php';
include 'login-verifier.php';
$film = curl_get($END_POINT.'/film?code_film='.$_GET['code_film'], $_GET);
$film = json_decode($film, true);
?>

Titre : <?=$film['titre']?> <br />
Année de sortie : <?=$film['annee']?> <br />
Pays de production :
<ul>
    <?php foreach ($film['pays'] as $pays) { ?>
        <li><?=$pays?></li>
    <?php } ?>
</ul>
Langue originale : <?=$film['langue']?> <br />
Durée (en minutes) : <?=$film['duree']?> <br />
Genres du film :
<ul>
    <?php foreach ($film['genres'] as $genre) { ?>
        <li><?=$genre?></li>
    <?php } ?>
</ul>
Nom du réalisateur : <?=$film['realisateur']?> <br />
Acteurs - Personnage :
<ul>
    <?php foreach ($film['acteurs'] as $acteur=>$personnage) { ?>
        <li><?=$acteur?> - <?=$personnage?></li>
    <?php } ?>
</ul>
Résumé du scénario : <?=$film['resume_film']?> <br />
L’affiche du film : <br/><a href="<?=$film['affiche']?>"><img src="<?=$film['affiche']?>"></a> <br />
Le lien vers toutes les bandes-annonces du film :
<ul>
    <?php foreach ($film['liens'] as $lien) { ?>
        <li><a href="<?=$lien?>"><?=$lien?></a></li>
    <?php } ?>
</ul>

<form method="post" action="reserver.php">
    <input type="hidden" name="code_film" value="<?=$film['code_film']?>" />
    <button>Louer</button>
</form>

<a href="films.php"><button>Retour</button></a>