<?php
include 'header.php';
include 'login-verifier.php';
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    
    $succes = curl_post($END_POINT.'/films/'. $_POST['code_film'] . '/reserver', ['client'=>$_SESSION['user']]);
    
    if ($succes == 'true') {
        echo 'Location effectuée avec succès.';
    } else {
        echo 'Il ne reste plus assez de copies.';
    }
}
?>

<a href="film.php?code_film=<?=$_POST['code_film']?>"><button>Retour</button></a>