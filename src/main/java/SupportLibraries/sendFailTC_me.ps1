 param([String]$filePath)

$smtpServer = "10.64.33.40"
$smtpFrom = "winprod@ri.com"
$messageSubject = "DEVW Standard Smoke test Execution Failure details"

$message = New-Object System.Net.Mail.MailMessage
$message.Subject = $messageSubject
$message.IsBodyHTML = $true
$message.Body = Get-Content $filePath
$message.from = 'winprod@ri.com'
$message.to.add('sanupati@deloitte.com')
$smtp = New-Object Net.Mail.SmtpClient($smtpServer)
$smtp.Send($message)