 param([String]$filePath)

$smtpServer = "10.64.33.40"
$smtpFrom = "winprod@ri.com"
$messageSubject = "SITM Parallel Smoke test Execution Summary"

$message = New-Object System.Net.Mail.MailMessage
$message.Subject = $messageSubject
$message.IsBodyHTML = $true
$message.Body = Get-Content $filePath
$message.from = 'winprod@ri.com'
$message.to.add('dababu@deloitte.com')

$smtp = New-Object Net.Mail.SmtpClient($smtpServer)
$smtp.Send($message)