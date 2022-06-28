<?php
session_start();
if ($_SESSION['user'] === null) {
    header('Location: login.php');
} else { ?>
    <a href="index.php">Bienvenue</a>, <?=$_SESSION['user']?>
<?php } ?>
<br/>