package com.db.hierarchicaltorelationaldm;

public class XQueryToSQLTranslator {

    public static String translateInsertQuery(String xquery) throws Exception {
        // Example XQuery: insert into Employee (Name, Age) values ('Alice', 30)

        if (!xquery.startsWith("insert into")) {
            throw new Exception("Invalid Insert XQuery format");
        }

        // Split into main parts
        String[] intoParts = xquery.split("into");
        String[] tableAndRest = intoParts[1].trim().split("\\(", 2);
        String tableName = tableAndRest[0].trim();

        // Extract columns and values
        String[] columnsAndValues = tableAndRest[1].split("values");
        String columns = columnsAndValues[0].trim();
        String values = columnsAndValues[1].trim();

        // Ensure columns and values are properly wrapped with parentheses
        if (!columns.endsWith(")")) {
            throw new Exception("Column list must end with ')'");
        }

        return "INSERT INTO " + tableName + " (" + columns + " VALUES " + values;
    }

    public static String translateReadQuery(String xquery) throws Exception {
        // Example XQuery: for $x in Employee where $x/Age > 30 return $x/Name

        // Very basic parsing for demo purpose
        if (!xquery.startsWith("for $x in")) {
            throw new Exception("Invalid Read XQuery format");
        }

        String[] parts = xquery.split("where");
        String fromPart = parts[0].split("in")[1].trim();
        String wherePart = parts[1].split("return")[0].trim();
        String selectPart = parts[1].split("return")[1].trim();

        String tableName = fromPart;
        String whereCondition = wherePart.replace("$x/", "");
        String columnName = selectPart.replace("$x/", "");

        return "SELECT " + columnName + " FROM " + tableName + " WHERE " + whereCondition;
    }

    public static String translateUpdateQuery(String xquery) throws Exception {
        // Example XQuery: update Employee set Age = 40 where Name = 'John'

        if (!xquery.startsWith("update")) {
            throw new Exception("Invalid Update XQuery format");
        }

        String[] parts = xquery.split("set");
        String tableName = parts[0].replace("update", "").trim();
        String[] setAndWhere = parts[1].split("where");

        String setClause = setAndWhere[0].trim();
        String whereClause = setAndWhere[1].trim();

        return "UPDATE " + tableName + " SET " + setClause + " WHERE " + whereClause;
    }

    public static String translateDeleteQuery(String xquery) throws Exception {
        // Example XQuery: delete from Employee where Age < 25

        if (!xquery.startsWith("delete from")) {
            throw new Exception("Invalid Delete XQuery format");
        }

        String[] parts = xquery.split("where");
        String tableName = parts[0].replace("delete from", "").trim();
        String whereClause = parts[1].trim();

        return "DELETE FROM " + tableName + " WHERE " + whereClause;
    }
}