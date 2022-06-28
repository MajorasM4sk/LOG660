<?php
include 'header.php';
include 'login-verifier.php';
$films = curl_get($END_POINT . '/films', $_GET);
$films = json_decode($films, true); ?>

Recherche
<form>
    Titre <input name="titre" /><br />
    Année min <input name="anneemin" /><br />
    Année Max <input name="anneemax" /><br />
    Pays <input name="pays" /><br />
    Langue <input name="langue" /><br />
    Genre <input name="genre" /><br />
    Réalisateur <input name="realisateur" /><br />
    Acteur <input name="acteur" /><br />
    <button>Rechercher</button>
</form>

<?php if (count($films) === 0) { ?>
    Si aucun résultat n'apparait, modifiez les paramètres de recherche.
<?php } else { ?>
    Films :
    <ul>
        <?php foreach ($films as $film) { ?>
            <li>
                <a href="film?code_film=<?=$film['code_film']?>"><?=$film['titre']?></a>
            </li>
        <?php } ?>
    </ul>
<?php } ?>