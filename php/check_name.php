<?php

$name = $_POST['name'];
$id = $_POST['id'];

if(isset($name))
{
	$dbHandle = mysql_connect('localhost','root','pwd');
	mysql_set_charset("utf8", $dbHandle);
	if ($dbHandle == False)
	{
		print ('can not connect dbn');	
		exit;
	}
	
	$db = 'map';
	$sql = "SELECT name FROM icon WHERE name <> '$name' AND id = '$id'";
	$rs = mysql_db_query($db,$sql);
		
	if(mysql_num_rows($rs) > 0)
	{
		$before_name = mysql_fetch_assoc($rs);
		echo $before_name[name];
	}
	else
	{
		echo $name;
	}
	mysql_free_result($rs);
	mysql_close($dbHandle);
	
}
?>	