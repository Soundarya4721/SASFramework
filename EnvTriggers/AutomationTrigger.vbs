Dim ServerShare
Dim UserName
Dim Password

Dim oShell
Dim NetworkObject
Dim objTextStream
Dim value
Dim FSO
Dim remoteLogin
Dim remoteLoginPassword

On error Resume Next

remoteLoginPassword = "Password"
ServerShare = "\\10.64.33.40\AutomationFolder\SmokeTestingTrigger"
UserName = "uhip\userName"
Password = "Password"

sFileName = ""&ServerShare&"\EnvName.txt"

Set oShell = CreateObject("WScript.Shell")
Set NetworkObject = CreateObject("WScript.Network")
remoteLogin = NetworkObject.UserName
NetworkObject.RemoveNetworkDrive "S:"
NetworkObject.MapNetworkDrive "S:", ServerShare, False, UserName ,Password


Set FSO = CreateObject("Scripting.FileSystemObject")
	Set objTextStream = FSO.OpenTextFile (sFileName, 1)
	Do Until objTextStream.AtEndOfStream
     value= objTextStream.ReadLine
	 value = Ucase(Trim(value))
	 REm wscript.echo value
    Loop
objTextStream.close

If Len(value) = 0 Then
Rem Wscript.Echo  "Nothing to Run"
Else 
REM oShell.Run "C:\Users\shivarpathak\Documents\RemoteExecution\"&(Trim(UCase(value)))&".bat"
oshell.Run "cmd.exe /C   C:\PS\psexec64 \\USBOSRIAPPS-R11  -u US\"&remoteLogin&" -p "&remoteLoginPassword&"  -i  C:\RI_Smoke_Framework\EnvTriggers\"&(Trim(UCase(value)))&".bat"
oshell.Run "cmd.exe /C   C:\PS\psexec64 \\USBOSRIAPPS-R14  -u US\"&remoteLogin&" -p "&remoteLoginPassword&"  -i  C:\RI_Smoke_Framework\EnvTriggers\"&(Trim(UCase(value)))&".bat"
oshell.Run "cmd.exe /C   C:\PS\psexec64 \\USBOSRIAPPS-R17  -u US\"&remoteLogin&" -p "&remoteLoginPassword&"  -i  C:\RI_Smoke_Framework\EnvTriggers\"&(Trim(UCase(value)))&".bat"

End If
Rem - Disconnect Drive for next Run
NetworkObject.RemoveNetworkDrive "D:"


Set objTextStream = Nothing
Set FSO = Nothing
Set NetworkObject = Nothing
Set oShell = Nothing

Rem - Clear the text file for another Run;
Set FSO2 = CreateObject("Scripting.FileSystemObject")
Set objTextStream2= FSO2.OpenTextFile (sFileName,2)
objTextStream2. Write ""
objTextStream2.close


Set sFileName = Nothing
Set FSO2 = Nothing
Set objTextStream2 = Nothing