<?php
session_start();
$conf = parse_ini_file(__DIR__ . '/../conf/db.ini');
$dsn = 'mysql:host=' . $conf['host'] . ';port=' . $conf['port'] . ';dbname=' . $conf['dbname'] . ';charset=' . $conf['charset'];
$db = new PDO($dsn, $conf['username'], $conf['password']);
	

$a=$_SESSION['id'];
$c="s".$_SESSION['time'];
$sql = "UPDATE pleasure\n"
. "SET $c='$_POST[Pleasure]'\n"
. "WHERE id=$a";
$stmt = $db->query($sql); //Ö´ÐÐSQL

$sql = "UPDATE arousal\n"
. "SET $c='$_POST[Arousal]'\n"
. "WHERE id=$a";
$stmt = $db->query($sql);

$_SESSION['no']=$_SESSION['no']+1;

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
$a=$a+1;
$b=$b+1;
if($a==5){
	$a=1;
	$b=$b+1;
}
$sql = "UPDATE state\n"
. "SET NexttID=$a, NextTime=$b";
$stmt = $db->query($sql); //Ö´ÐÐSQL
$b=$_SESSION['no'];
if($b<3)
{
	require_once __DIR__ . '/../res/index.html';
}
else
{	
	
	require_once __DIR__ . '/../res/end_page.html';
}
	



