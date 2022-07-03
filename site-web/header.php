<?php

$END_POINT = 'http://localhost:8080/log660';

function curl_post($url, $params) {
    //https://stackoverflow.com/questions/19499891/php-post-to-another-server-then-return-the-other-servers-response
    $postvars='';
    $sep='';
    foreach($params as $key=>$value) {
        $postvars .= $sep.urlencode($key) . '=' . urlencode($value);
        $sep='&';
    }

    $ch = curl_init();

    curl_setopt($ch,CURLOPT_URL,$url);
    curl_setopt($ch,CURLOPT_POST,count($params));
    curl_setopt($ch,CURLOPT_POSTFIELDS,$postvars);
    curl_setopt($ch,CURLOPT_RETURNTRANSFER,true);

    $result = curl_exec($ch);
    curl_close($ch);

    return $result;
}

function curl_get($url, $params) {
    $ch = curl_init();

    $url .= '?' . http_build_query($params);

    curl_setopt($ch,CURLOPT_URL, $url);
    curl_setopt($ch,CURLOPT_RETURNTRANSFER, true);

    $result = curl_exec($ch);
    curl_close($ch);
    return $result;
}
?>