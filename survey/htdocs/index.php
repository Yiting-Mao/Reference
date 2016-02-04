<?php
session_start(); 
$conf = parse_ini_file(__DIR__ . '/../conf/db.ini');
$dsn = 'mysql:host=' . $conf['host'] . ';port=' . $conf['port'] . ';dbname=' . $conf['dbname'] . ';charset=' . $conf['charset'];
$db = new PDO($dsn, $conf['username'], $conf['password']);
$db->setAttribute(PDO::ATTR_CASE, PDO::CASE_NATURAL);
$rs = $db->query(" SELECT * FROM state");
$rs->setFetchMode(PDO::FETCH_ASSOC);
$states = $rs->fetchAll();
$a=$states[0]['NexttID'];
$b=$states[0]['NextTime'];
$t2='../images/'.$a.'.jpg';
list($width,$height,$type,$attr)=getimagesize($t2);

$_SESSION['id']=$a;
$_SESSION['time']=$b;
$_SESSION['no']=0;

$a=$a+1;
if($a==5){
	$a=1;
	$b=$b+1;
}
$sql = "UPDATE state\n"
. "SET NexttID=$a, NextTime=$b";
$stmt = $db->query($sql); //Ö´ÐÐSQL

require_once __DIR__ . '/../res/index.html';

