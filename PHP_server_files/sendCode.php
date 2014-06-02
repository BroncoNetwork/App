<?php
$username = $_POST['username'];
$email = $_POST['email'];
$code = $_POST['code'];

$con=mysqli_connect("mysql6.000webhost.com","a7453331_admin","bronco909","a7453331_CS356");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

$result = mysqli_query($con,"SELECT username,email FROM User Where username = '$username' or email= '$email'");

if (mysqli_error($con))
  { echo "Failed to connect to MySQL: " . mysqli_connect_error();} 

$row = mysqli_fetch_array($result);

if(!$row[0] == $username and !$row[1] == $email)
{
	$to      = $email;
	$subject = 'the subject';
	$message = 'Hi ' . $username . ' This is your validation code: ' . $code;
	$headers = 'From: officialbronconetwork@gmail.com' . "\r\n" .
		'Reply-To: officialbronconetwork@gmail.com' . "\r\n" .
		'X-Mailer: PHP/' . phpversion();

	if(mail($to, $subject, $message, $headers))
	{
	 echo "Sent email already";
	}
	else
	 echo "Cannot send email";
}
else if($row[0] == $username)
{
	echo " Username has been taken";
}
else
{
        echo "Email has been taken";
}
mysqli_close($con);
?>