<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<script type="text/javascript" src="/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<form action="/smarteditor/insert.do" method="post" id="smarteditorForm">
<textarea class="form-control" rows="20" name="bo_content" id="bo_content">${smateditionVO.bo_content}</textarea>
<button type="submit" id="formBtn">제출</button>
</form>


<script>

var oEditors = [];

nhn.husky.EZCreator.createInIFrame({
  oAppRef : oEditors,
  elPlaceHolder : "bo_content",
  sSkinURI : "/smarteditor2/SmartEditor2Skin.html",
  fCreator : "createSEditor2"
});
form



</script>

</body>
</html>
