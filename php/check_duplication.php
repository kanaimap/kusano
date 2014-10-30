<?php

$name = $_POST['name'];

if(isset($name))
{

	$dbHandle = mysql_connect('localhost','root','pwd');
	mysql_set_charset("utf8", $dbHandle);
	if($dbHandle == False)
	{
		print('can not connect dbn');
		exit;
	}
	
	$db = 'map';
	
	$sql = "SELECT name FROM icon WHERE name = '$name'";
	$rs = mysql_db_query($db,$sql);
	
	if(mysql_num_rows($rs) == 0)
	{
		echo "OK";
		
	}
	
	else
	{
		echo "duplication";
	}

	mysql_free_result($rs);

	mysql_close($dbHandle);
}

?>