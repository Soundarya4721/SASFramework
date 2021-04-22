Dim sFileName
Dim sBatchFileName
Dim Enviroinment
Dim shell
Dim strPath

	Set shell = CreateObject("WScript.Shell")
	    strPath = Replace(shell.CurrentDirectory,"\EnvTriggers","")
	 
	sFileName = ""&strPath&"\Env.properties"

	Set args = WScript.Arguments
	Environment = args.Item(0)

	Set objFSO = CreateObject("Scripting.FileSystemObject")
	if objFSO.fileExists(sFileName) Then
	set objTextStream = objFSO.OpenTextFile (sFileName, 2)
	objTextStream.WriteLine("EnvironmentName="&Environment)
	objTextStream.Close
	Set objTextStream = nothing
	Set objFSO = nothing
	End if
	
    shell.Run "C://SmokeFramework//Automation_Framework//Start_StandaloneExecution.bat"
	Set shell = nothing

