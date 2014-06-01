<?php
 
if (empty($_POST['id']))
  { echo 'You haven\'t filled out all fields correctly. Please go back and try again.'; } 
else
{
$con=mysqli_connect("mysql6.000webhost.com","a7453331_admin","bronco909","a7453331_CS356");
 
 if (mysqli_connect_errno($con))
 {
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
 }
 else
 {
  $id = $_POST['id'];
  $result = mysqli_query($con,"SELECT * FROM Comments Where root='$id' ORDER BY id");
 
  while($row = mysqli_fetch_array($result))
  {
     echo $row[1]."|".$row[2]."|".$row[3]."|".$row[4]."`";
  }
 } 
 mysqli_close($con);
}
?>