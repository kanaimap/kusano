<?php

$dbHandle = mysql_connect('localhost','root','pwd');
mysql_set_charset("utf8", $dbHandle);
if ($dbHandle == False)
	{
		print ('can not connect dbn');	
		exit;
	}
$db = 'map';
$sql = "SELECT location.name,location.latitude,location.longitude,location.time,location.comment,icon.icon_id FROM location,icon WHERE location.name = icon.name AND location.comment = '今ここ!'  ORDER BY icon.id , location.time";
$result = mysql_db_query($db,$sql);
while ($row = mysql_fetch_assoc($result)) {
    $users[] = array(
		'name' => $row['name'],
		'latitude' => $row['latitude'],
		'longitude' => $row['longitude'],
		'time' => $row['time'],
		'icon_id' => $row['icon_id'],
		'comment' => $row['comment']
	);
}	
	
header('Content-Type: application/json');
echo json_encode($users);
exit;

?>
