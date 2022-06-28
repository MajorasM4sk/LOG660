<?php
include 'header.php';
session_start();
$_SESSION['user'] = null;
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    
    $result = curl_post($END_POINT.'/login', $_POST);

    if ($result == 'true') {
        $_SESSION['user'] = $_POST['email'];
        header('Location: index.php');
    } else {
        echo "L'email ou le mot de passe sont incorrects.";
    }
}
?>
<body>
    <form method="post">
        <input id="email" name="email" />
        <input id="password" type="password" name="password" />
        <button>Se connecter</button>
    </form>
    </script>
</body>