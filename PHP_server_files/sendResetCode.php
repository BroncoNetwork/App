<?php
$email = $_POST['email'];
$code = $_POST['code'];

$con=mysqli_connect("mysql6.000webhost.com","a7453331_admin","bronco909","a7453331_CS356");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

$result = mysqli_query($con,"SELECT username,email FROM User Where email= '$email'");

if (mysqli_error($con))
  { echo "Failed to connect to MySQL: " . mysqli_connect_error();} 

$row = mysqli_fetch_array($result);

if($row[1] == $email)
{
	$to      = $email;
	$subject = 'the subject';
	$message = 'Hi ' . $row[0] . ' This is your validation code: ' . $code;
	$headers = 'From: officialbronconetwork@gmail.com' . "\r\n" .
		'Reply-To: officialbronconetwork@gmail.com' . "\r\n" .
		'X-Mailer: PHP/' . phpversion();

	if(mail($to, $subject, $message, $headers))
	{
		echo $row[0] . "\n" ;
	}
	else
		echo "Cannot send email";
}
else
{
	echo "Email is not in the system \n";
}
mysqli_close($con);
?>