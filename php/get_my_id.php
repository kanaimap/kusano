<?php

$name = $_POST['name'];

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
	$sql = "SELECT id FROM icon WHERE '$name' = name";
	
	$result = mysql_db_query($db,$sql); 
	$id = mysql_fetch_assoc($result);
	echo $id[id];
	mysql_free_result($result);
	mysql_close($dbHandle);
}
?>	