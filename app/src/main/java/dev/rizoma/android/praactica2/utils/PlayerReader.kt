package dev.rizoma.android.praactica2.utils

import android.provider.BaseColumns

object PlayerReader {
    // Table contents are grouped together in an anonymous object.
    object PlayerEntry : BaseColumns {
        const val TABLE_NAME = "players"
        const val _ID = "id"
        const val NAME = "name"
        const val LAST_NAME = "last_name"
        const val AGE = "age"
        const val NATIONALITY = "nationality"
        const val TEAM = "current_team"
        const val GOALS = "goals"
        const val BORN = "birthday"

    }
    public val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${PlayerEntry.TABLE_NAME} (" +
                "${PlayerEntry._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${PlayerEntry.NAME} TEXT," +
                "${PlayerEntry.LAST_NAME} TEXT," +
                "${PlayerEntry.AGE} INTEGER," +
                "${PlayerEntry.NATIONALITY} TEXT," +
                "${PlayerEntry.TEAM} TEXT," +
                "${PlayerEntry.GOALS} INTEGER," +
                "${PlayerEntry.BORN} TEXT)"

    public const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${PlayerEntry.TABLE_NAME}"
    public const val SQL_GET_LATEST_ENTRY = "SELECT * FROM ${PlayerEntry.TABLE_NAME} ORDER BY ${PlayerEntry._ID} DESC LIMIT 1 "


}