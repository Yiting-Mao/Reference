<?php

session_start();
$conf = parse_ini_file(__DIR__ . '/../conf/db.ini');
$dsn = 'mysql:host=' . $conf['host'] . ';port=' . $conf['port'] . ';dbname=' . $conf['dbname'] . ';charset=' . $conf['charset'];
$db = new PDO($dsn, $conf['username'], $conf['password']);

$a=$_SESSION['id'];
$c=$_SESSION['time'];


$sql = "INSERT INTO account\n"
. "VALUES ('$_POST[account]',$a,$c)\n";

$stmt = $db->query($sql); //Ö´ÐÐSQL


echo "Thank you for your assistance";
	
