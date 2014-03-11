<%@ taglib uri="http://displaytag.sf.net/el" prefix="display"%>  
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>displaytag</title>
</head>
<body>
		<div align="center">
		<display:table name="userlist"  cellspacing="0" cellpadding="0" pagesize="5" export="true" requestURI="">
			<display:column property="uuid" title="ID"  sortable="true"/>
			<display:column property="email" title="email" sortable="true"/>
			<display:column property="password" title="password"/>
			<display:column property="description" title="description" />
			<display:column property="nickname" title="nickname" />
			<display:column property="firstName" title="firstName" />
			<display:column property="lastName" title="lastName" />
			<display:column property="phoneNumber" title="phoneNumber" />
			<display:column property="authorities" title="authorities" />
			<display:column property="enabled" title="enabled" />
			<display:column property="accountExpired" title="accountExpired" />
			<display:column property="accountLocked" title="accountLocked" />
			<display:column property="credentialsExpired" title="credentialsExpired" />
			
			<display:setProperty name="export.excel.filename" value="Post List.xls"/>
	        <display:setProperty name="export.csv.filename" value="Post List.csv"/>
	        <display:setProperty name="export.pdf.filename" value="Post List.pdf"/>
		</display:table>
<% 
/*
<display:table name="test" cellspacing="0" cellpadding="0" pagesize="2" export="true">
<display:table name="testList" sort="external" defaultsort="1" pagesize="20" id="element" partialList="true" size="resultSize"
�ñ�ǩ�����������һ�����name����Ϊ���ܵ�����,�˱�ǩ����ľ���test��������ݡ�
pagesizeΪ�ñ�ǩ�ķ�ҳ���ܣ�ָ��pagesize�Ĵ�С��ָ����һ��ҳ����ʾ���������������ṩ��ҳ��������
export="true"Ϊ��ʾ��ҳ���Ƿ��ṩ�������ܣ�Ŀǰ�ṩcsv,xls,pdf�������ʽ��
sort="external"  ����DisplayTag��������ݼ��Ѿ����ⲿ�����ź�����..
defaultsort="1" ˵��Ĭ��������(Descending is 2, Ascending is 1);
partialList="true" ˵������װ������;
pagesize="20" ÿҳ��ʾ��¼��;
size="resultSize" ��ʾ��¼��������(�˲������PageSize��ʹ�ñ����ֻ�õ�ĳһҳ���������ݵ�ͬʱ������֪�����ж���ҳ������������ҳ��Ҳ�оٳ��������û�ʵ�ʷ�ҳʱ��ȥ��ȡ��ҳ����);
requestURI="" �趨table������Ϊ�գ����򽫻���jsp�ļ���·��

display:column��ʵ����ʾ�����ÿ�����ݡ��Դ˶δ���Ϊ�����������������¼����Ϊid�����ݡ�����ʾ����ΪID,sortable="true"�ṩ�Ĺ����Ǹ����Ƿ��ṩ�����ܡ�

Name���ԣ�����ָ������ʾscope�е����ݱ�־��ͨ��name������scope�е����ݲ�������ʾ������ָ��pageScope��requestScope��sessionScope��applicationScope������requestScope��ȱʡ��scope�����������request.setAttribute(��Infolist��,list)����ʽ����requestScope����ôname���Կ���ֱ����[name=��Infolist��]����ʽָ��������sessionScope�Ļ�����Ҫ�������������������ʽָ���ˡ�

Id���ԣ�ָ����ʾ����Ψһ��־���ں��������ͨ��yourID_rowNum����ʽ��ʾ����ʹ��ÿ�����ݵ��кš����磺���ָ��id=��tableID������ô��< %=tableID_rowNum% >�ͻ�������ݵ��кš���ָ��id����һ�����þ��ǣ����һ��ҳ�����ж����ҳ��ʾ���Ļ���ָ��id�󣬸������ķ�ҳ�Ϳ��Թ���������

Export���ԣ���Ҫָ��boolean�͵�ֵ�����ָ��export=true�Ļ��������ʾ��ɺ��������һ���������Ŀ��ָ�����ݵ�����ѡ����򣬲���ʾ���ݵ�����Ŀ��Ĭ��Ϊfalse��

Pagesize���ԣ�ָ��ÿҳ�����ʾ���������������Ҫ��ʾ�����ݼ�¼�ܳ��Ļ���ָ��pagesize�����ݽ�����pagesize����ָ������Ŀ��ʾ��¼�������������ݽ��ֶ��ҳ����ʾ�������ָ�������ԣ��������ݽ���һ��ҳ����ʾ��

Class���ԣ�ָ�������ʾ��Ҫʹ�õ�css���displaytag�ṩ��ISIS��ITS��Mars��Simple��Report���ַ��Ĭ����ISIS��Ҳ��������Ļ�ɫɫ���ķ����Щ�������screen.css�ļ��ж���ģ����Ը�����Ҫ�޸Ļ��������Ҫ�ķ��

RequestURI���ԣ��������Ҫ���ݵ�����������߷�ҳ��ʾ��ʱ����ΪҪ�ύ��ָ����URL������������Ծ������������ġ�

Sort���ԣ�����ָ�������ݽ��������ʱ���Ƕ�����������list����������ֻ�Ե�ǰҳ������ݽ�������Ĭ�ϵĲ�ָ�������Ե�����£������ʱ��ֻ�Ե�ǰҳ�����ݽ����������ָ��sort����list���Ļ�������Զ�����������list��������


http://www.cnblogs.com/chinafine/articles/1801344.html
*/		
%>
</body>
</html>