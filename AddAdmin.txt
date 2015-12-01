Public Class AddAdmin

    Dim prevUsername As String = ""
    Dim saveMode As String

    Private Sub AddAdmin_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        ' secondaryButtons are the Save,Cancel and Clear buttons
        ' we are hiding them because you need to click New or Update before the
        ' save, cancel and clear shows when the form shows
        hideSecondaryButtons()
        ' mainButtons are the buttons New,Update and Delete
        ' we need to show the New, Update and delete for the user to use
        showMainButtons()
        'disable the fields to 
        ' we disable the fields so that you can only edit the text boxes when Update or New
        ' is clicked
        disableFields()
        ' reload the grid of the values in the database
        reloadAdmins()
    End Sub

    Sub reloadAdmins()
        ' get all the records WHERE the user_type is admin
        Dim gift = query("SELECT user_id as `ID`,user_uname as `Username`," +
                              "user_fname as `Firstname`,user_lname as `Lastname`," +
                              "user_password as `Password`," +
                              "user_gender as `Gender`,user_address as `Address`" +
                              ",user_email as `Email`,user_phone as `Phone`," +
                              "user_birthday as `Birthday` FROM `user` WHERE user_type='ADMIN'")
        ' create a datatable to hold the data and eventually asign this to the DataGridView for
        ' the data to show in the grid
        Dim dt = New DataTable()
        ' Load the data we query , this automatically insert rows to our data table
        dt.Load(gift)
        ' After we insert the data to the data table we need to assign the it to the DataGridView's datasource
        ' this will make the DataGridView display our data
        dgvAdmins.DataSource = dt
        ' We are hiding some columns because displaying all of the columns will take too much space on the screen
        dgvAdmins.Columns(4).Visible = False ' Hide the password column
        dgvAdmins.Columns(5).Visible = False ' Hide the gender column
        dgvAdmins.Columns(6).Visible = False ' Hide the address column
        dgvAdmins.Columns(8).Visible = False ' Hide the phone column
        dgvAdmins.Columns(9).Visible = False ' hide the birthday column
        ' the number represents the column's position in the query starting at 0
        ' dgvAdmins.Columns(4).Visible we hide the column 5 which is gender
        ' since the starting count is 0 

        'close the connection every after query
        closeConnection()
    End Sub

    ' Disable all fields
    Sub disableFields()
        txtAddress.Enabled = False
        txtUserEmail.Enabled = False
        txtUserFirstName.Enabled = False
        txtUserLastName.Enabled = False
        txtUserName.Enabled = False
        txtUserPassword.Enabled = False
        txtUserPhone.Enabled = False
        rblFemale.Enabled = False
        rblMale.Enabled = False
        dtpUserBirthday.Enabled = False
    End Sub

    ' Enable all fields
    Sub enableFields()
        txtAddress.Enabled = True
        txtUserEmail.Enabled = True
        txtUserFirstName.Enabled = True
        txtUserLastName.Enabled = True
        txtUserName.Enabled = True
        txtUserPassword.Enabled = True
        txtUserPhone.Enabled = True
        rblFemale.Enabled = True
        rblMale.Enabled = True
        dtpUserBirthday.Enabled = True
    End Sub

    ' Show Save, Cancel, Clear
    Sub showSecondaryButtons()
        btnSave.Visible = True
        btnClear.Visible = True
        btnCancel.Visible = True
    End Sub

    ' Hides Save, Cancel, Clear
    Sub hideSecondaryButtons()
        btnSave.Visible = False
        btnClear.Visible = False
        btnCancel.Visible = False
    End Sub

    'Show New, Delete, Update
    Sub showMainButtons()
        btnDelete.Visible = True
        btnUpdate.Visible = True
        btnNew.Visible = True
    End Sub

    'Hide New, Delete, Update
    Sub hideMainButtons()
        btnDelete.Visible = False
        btnUpdate.Visible = False
        btnNew.Visible = False
    End Sub

    ' This is the code executed whenever the user select a row in the grid
    ' so basically each time user change the selected row, we will load the
    ' datagridview values to each textbox
    Private Sub dgvUser_SelectionChanged(sender As Object, e As EventArgs) Handles dgvAdmins.SelectionChanged
        ' everytime when we select row in the grid it will extract data to the fields
        ' we need to make sure that their is a selected row before assigning values to each textbox
        If dgvAdmins.SelectedRows.Count > 0 Then
            ' SelectedRows(0) 0 means the first selected row, since we only allow single row selection
            ' on the datagridview that is automatic as 0 always.
            '.Cells(0) means we are getting the selected row's 1st cell which is the ID,
            ' we caan get hidden cells aswell thats why we only hide them in the reloadAdmins()
            ' because if we didnt include them in the query we cant get or display those fields
            Dim id As Integer = dgvAdmins.SelectedRows(0).Cells(0).Value
            Dim username As String = dgvAdmins.SelectedRows(0).Cells(1).Value
            Dim fname As String = dgvAdmins.SelectedRows(0).Cells(2).Value
            Dim lname As String = dgvAdmins.SelectedRows(0).Cells(3).Value
            Dim password As String = dgvAdmins.SelectedRows(0).Cells(4).Value
            Dim gender As String = dgvAdmins.SelectedRows(0).Cells(5).Value
            Dim address As String = dgvAdmins.SelectedRows(0).Cells(6).Value
            Dim email As String = dgvAdmins.SelectedRows(0).Cells(7).Value
            Dim phone As String = dgvAdmins.SelectedRows(0).Cells(8).Value
            Dim birthdate As Date = dgvAdmins.SelectedRows(0).Cells(9).Value

            ' set each values to their textbox
            txtAddress.Text = address
            txtUserEmail.Text = email
            txtUserFirstName.Text = fname
            txtUserLastName.Text = lname
            txtUserName.Text = username
            txtUserPassword.Text = password
            txtUserPhone.Text = phone
            dtpUserBirthday.Value = birthdate

            ' if the gender to upper case text is equal to MALE then
            ' we will select the rblMale else we select rblFemale
            If gender.ToUpper = "MALE" Then
                rblMale.Checked = True
                rblFemale.Checked = False
            Else
                rblMale.Checked = False
                rblFemale.Checked = True
            End If
        End If
    End Sub

    Private Sub btnDelete_Click(sender As Object, e As EventArgs) Handles btnDelete.Click
        ' before deleting we need to check if there are really selected user in the grid
        ' we cant delete a record when there is no selected row , because we will
        ' get the selected rows 1st cell which is it's id to query and delete it,
        ' if there is no selected then we cant get that id
        If dgvAdmins.SelectedRows.Count = 0 Then
            showError("Please select an admin in the grid")
            Exit Sub
        End If
        ' get the id of the selected user
        Dim selectedID = dgvAdmins.SelectedRows(0).Cells(0).Value
        ' ask the user if he/she really want to delete the account
        ' we assign the button that will be press in the dialog at the result variable
        Dim result = MessageBox.Show("Are you sure you want to delete this admin ?", "", MessageBoxButtons.YesNo, MessageBoxIcon.Question)
        ' we check if the user press Yes and not No before we delete
        If result = Windows.Forms.DialogResult.Yes Then
            ' delete the user whose id is equal to the selected rows id
            nonQuery("DELETE FROM `user` WHERE user_id = " & selectedID)
            ' close connection every after query
            closeConnection()
            ' we need to reload the DataGridView admin to reflect the changes in our database
            reloadAdmins()
        End If
    End Sub

    Private Sub btnNew_Click(sender As Object, e As EventArgs) Handles btnNew.Click
        ' Clear all the textboxs value
        clear()
        ' we use savemode to determine if we creating new or updating a current record
        ' since we only have one save button , we need to determine , if the save button is
        ' for New, or Update , we need to 
        saveMode = "new"
        ' enable the fields so that the user can edit the fields
        enableFields()
        ' hide the main buttons
        hideMainButtons()
        ' show the secondary buttons
        showSecondaryButtons()
        ' disable the grid after clicking new,
        ' because if the user select a row in the grid the dgvUser_SelectionChanged will run
        ' and will change the textboxs values
        dgvAdmins.Enabled = False
    End Sub

    Private Sub btnCancel_Click(sender As Object, e As EventArgs) Handles btnCancel.Click
        disableFields()
        showMainButtons()
        hideSecondaryButtons()
        ' Enable the grid so the user can select rows
        dgvAdmins.Enabled = True
        ' we call directly the dgvUser_SelectionChanged to force the grid to load,
        ' the textboxs values
        dgvUser_SelectionChanged(Nothing, Nothing)
    End Sub

    Private Sub btnClear_Click(sender As Object, e As EventArgs) Handles btnClear.Click
        clear()
    End Sub

    Sub clear()
        txtAddress.Clear()
        txtUserEmail.Clear()
        txtUserFirstName.Clear()
        txtUserLastName.Clear()
        txtUserName.Clear()
        txtUserPassword.Clear()
        txtUserPhone.Clear()
    End Sub

    Private Sub btnSave_Click(sender As Object, e As EventArgs) Handles btnSave.Click
        Dim username As String = txtUserName.Text
        Dim fname As String = txtUserFirstName.Text
        Dim lname As String = txtUserLastName.Text
        Dim password As String = txtUserPassword.Text
        Dim email As String = txtUserEmail.Text
        Dim phone As String = txtUserPhone.Text
        Dim birthday As Date = dtpUserBirthday.Value.Date
        Dim address As String = txtAddress.Text
        Dim gender As String
        ' we automatically set the type to admin since we are in AddAdmin
        Dim type As String = "ADMIN"

        ' Field validation , Check if there is an error 
        ' the program wont execute further commands if it founds error
        If username.Length = 0 Then
            showError("Username field is empty")
            Exit Sub
        ElseIf fname.Length = 0 Then
            showError("First name field is empty")
            Exit Sub
        ElseIf lname.Length = 0 Then
            showError("Last name field is empty")
            Exit Sub
        ElseIf password.Length = 0 Then
            showError("Password field is empty")
            Exit Sub
        ElseIf address.Length = 0 Then
            showError("Address field is empty")
            Exit Sub
        ElseIf email.Length = 0 Then
            showError("Email field is empty")
            Exit Sub
        ElseIf phone.Length = 0 Then
            showError("Phone field is empty")
            Exit Sub
        ElseIf birthday = Nothing Then
            showError("Please choose a birthday")
            Exit Sub
        End If

        ' we need to make sure that the username entered is not already in the database,
        ' (prevUsername will be set in the update button) , when updating we can change the username,
        ' so we will record the username before the user change it and save it,
        ' if the previous username is equal to the username it means the user didnt change the 
        ' username so we wont check it, but if its not equal then we need to check if the new
        ' username is already taken or not

        'if the saveMode is new it means New button is click instead of update, so we will always
        ' check for username when creating another user since its a new user
        If Not prevUsername = txtUserName.Text Or saveMode = "new" Then
            ' Check wheather the username is already been taken
            Dim result = query("SELECT user_uname FROM `user` WHERE user_uname='" + username + "'")
            ' if the result has rows it means there is a record matching the username
            If result.HasRows Then
                showError("Username is already been taken")
                closeConnection()
                Exit Sub
            End If
            closeConnection()
        End If

        ' if the male radio button is click set the gender to MALE
        ' else then set it to FEMALE
        If rblMale.Checked Then
            gender = "MALE"
        Else
            gender = "FEMALE"
        End If

        ' if the save mode is new then INSERT the record
        ' else its an UPDATE
        If saveMode = "new" Then
            ' we are inserting a new user
            nonQuery("INSERT INTO `user`(user_email,user_fname,user_lname,user_gender,user_phone,user_password,user_address,user_birthday,user_uname,user_type) VALUES(" +
                     "'" + email + "'," +
                     "'" + fname + "'," +
                     "'" + lname + "'," +
                     "'" + gender + "'," +
                     "'" + phone + "'," +
                     "'" + password + "'," +
                     "'" + address + "'," +
                     "'" + birthday + "'," +
                     "'" + username + "'," +
                     "'" + type + "')")
            closeConnection()

            showMessage(username + " was created succesfully!")
            ' reload the datagridview so that the new admin will show on the grid
            reloadAdmins()
            ' dgvAdmins.Rows(<index to select>) since we set it to dgvAdmins.Rows.Count - 1 this will
            ' select the last added , why -1? , because the cont will always start at 0
            dgvAdmins.Rows(dgvAdmins.Rows.Count - 1).Selected = True
            'If the saveMode is not new then its an update
        Else
            'we save first get the id so that we can query the admin and update ot
            Dim id As Integer = dgvAdmins.SelectedRows(0).Cells(0).Value
            ' Update all fields except id
            nonQuery("UPDATE `user` SET " +
                     "user_email='" + email + "'," +
                     "user_fname='" + fname + "'," +
                     "user_lname='" + lname + "'," +
                     "user_gender='" + gender + "'," +
                     "user_phone='" + phone + "'," +
                     "user_password='" + password + "'," +
                     "user_address='" + address + "'," +
                     "user_birthday='" + birthday + "'," +
                     "user_uname='" + username + "' " +
                     "WHERE user_id = " & id)
            ' close connection after you execute query
            closeConnection()
            showMessage("User updated succesfull!")

            ' save the index of the currently selected records
            Dim previousSelected = dgvAdmins.SelectedRows(0).Index
            ' reload the dgv to reflect changes
            reloadAdmins()
            ' select again the previously updated record, since reloadAdmins will unselect the row
            dgvAdmins.Rows(previousSelected).Selected = True

            ' if the username that was being updated was the currently logg in admin then
            ' we need to update the Application.user to reflect the change from the database
            ' and correctly display the updated values in the user profile
            If prevUsername = Application.user.username Then
                Dim userQuery = query("SELECT * FROM `user` WHERE user_id=" & Application.user.id)
                ' read method read the first row
                userQuery.Read()
                ' Convert the result to an instance of user and set it to the application module
                Dim myUser = convertResultToUser(userQuery)
                ' set it to Application.user so that every form can access the currently log in user
                Application.user = myUser
                closeConnection()
            End If
        End If

        ' enable the grid to allow the user to change the selected row
        dgvAdmins.Enabled = True
        ' hide the secondary buttons after clicking save
        hideSecondaryButtons()
        ' show the main buttons
        showMainButtons()
        ' disable the fields after clicking save
        disableFields()
    End Sub

    Private Sub btnUpdate_Click(sender As Object, e As EventArgs) Handles btnUpdate.Click
        ' Check wheather there is a selected row before proceeding
        If dgvAdmins.SelectedRows.Count = 0 Then
            showError("Please select a user in the grid")
            Exit Sub
        End If
        ' save mode is update so that when we press save the save method will know if we are creating new
        ' record or updating existing one
        saveMode = "update"
        ' enable all the fields so that they are editable
        enableFields()
        'show the save, cancel, clear method
        showSecondaryButtons()
        ' hide the New,Update,Delet button
        hideMainButtons()
        ' dont let the user select another record while updating
        dgvAdmins.Enabled = False
        ' set the rows username to the prevUsername
        prevUsername = txtUserName.Text
    End Sub

    Private Sub btnMainPage_Click(sender As Object, e As EventArgs) Handles btnMainPage.Click
        MainPage.Show()
        Me.Close()
    End Sub

    Private Sub btnAdminProfile_Click(sender As Object, e As EventArgs) Handles btnAdminProfile.Click
        AdminProfile.Show()
        Me.Close()
    End Sub
End Class