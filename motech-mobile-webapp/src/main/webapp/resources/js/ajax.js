function getXMLHTTP()
{
    //Create a boolean variable to check for a valid Internet Explorer instance.
    var xmlhttp = false;
    var message = '';
    //Check if we are using IE.
    try
    {
        //If the Javascript version is greater than 5.
        xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
    }
    catch (e)
    {
        //If not, then use the older active x object.
        try
        {
            //If we are using Internet Explorer.
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        catch (E)
        {
            //Else we must be using a non-IE browser.
            xmlhttp = false;
        }
    }
    //If we are using a non-IE browser, create a javascript instance of the object.
    if (!xmlhttp && typeof XMLHttpRequest != 'undefined')
    {
        xmlhttp = new XMLHttpRequest();
    }
    return xmlhttp;
}

function makeRequest(serverPage, objID, method, params, loadingHTML)
{
    var obj = document.getElementById(objID);
    obj.innerHTML = loadingHTML;
    xmlhttp = getXMLHTTP();

    if(method == "GET")
    {
        xmlhttp.open("GET", serverPage);
        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState == 4){
                if(xmlhttp.status == 200) {
                    obj.innerHTML = '<div style=\'padding-left: 10px; padding-top: 20px;\'>'+xmlhttp.responseText.replace('\n','<br />','g', 'm')+'</div>';
                }
                else
                    obj.innerHTML = '<div style=\'padding-left: 10px; padding-top: 20px; width: 120px;\'>An error occurred! Please try again.</div>';
            }
        }
        xmlhttp.send(null);
    }else{
        xmlhttp.open("POST", serverPage, true);
        xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState == 4){
                if(xmlhttp.status == 200) {
                    obj.innerHTML = '<div style=\'padding-left: 10px; padding-right: 5px; padding-top: 20px;\'>'+xmlhttp.responseText.replace('\n','<br />','g', 'm')+'</div>';
                }
                else
                    obj.innerHTML = '<div style=\'padding-left: 10px; padding-right: 5px; padding-top: 20px; width: 120px;\'>An error occurred! Please try again.</div>';
            }
        }
        xmlhttp.send(params);
    }
}

function getFormValues(fobj)
{
    var str = "";
    var element = "";
    //Run through a list of all objects contained within the form.
    for(var i = 0; i < fobj.elements.length; i++)
    {
        element = fobj.elements[i].name + "=" + escape(fobj.elements[i].value) + "&";
        str += element;
    }
    //Then return the string values.
    return str;
}