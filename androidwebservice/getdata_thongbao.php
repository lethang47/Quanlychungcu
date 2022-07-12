<?php
$connect = mysqli_connect("localhost","root","","quanlychungcu");
mysqli_query($connect, "SET NAMES 'utf8'");

$query = "SELECT * FROM notification";
$data = mysqli_query($connect, $query);
class User{
	function User($id, $content, $date){
		$this->Id = $id;
		$this->Content = $content;
		$this->Date = $date;
	}
}

$mangUser = array();
while($row = mysqli_fetch_assoc($data)){
	array_push($mangUser, new User($row['id'], $row['content'], $row['date']));	
}
echo json_encode($mangUser);
?>

