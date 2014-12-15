<?php
$dbHandle = mysql_connect('localhost','root','pwd');
mysql_set_charset("utf8", $dbHandle);
if ($dbHandle == False)
	{
		print ('can not connect dbn');	
		exit;
	}
$db = 'map';
$sql = "SELECT name FROM icon";
$result = mysql_db_query($db,$sql);
while ($row = mysql_fetch_assoc($result)) {
    $users[] = array(
		'name' => $row['name']
	);
}	
	
header('Content-Type: application/json');
echo json_encode($users);
exit;

?>
