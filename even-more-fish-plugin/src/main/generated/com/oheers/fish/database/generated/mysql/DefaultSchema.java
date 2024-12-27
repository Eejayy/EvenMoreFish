/*
 * This file is generated by jOOQ.
 */
package com.oheers.fish.database.generated.mysql;


import com.oheers.fish.database.generated.mysql.tables.Competitions;
import com.oheers.fish.database.generated.mysql.tables.Fish;
import com.oheers.fish.database.generated.mysql.tables.FishLog;
import com.oheers.fish.database.generated.mysql.tables.Transactions;
import com.oheers.fish.database.generated.mysql.tables.Users;
import com.oheers.fish.database.generated.mysql.tables.UsersSales;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DefaultSchema extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>DEFAULT_SCHEMA</code>
     */
    public static final DefaultSchema DEFAULT_SCHEMA = new DefaultSchema();

    /**
     * The table <code>${table.prefix}competitions</code>.
     */
    public final Competitions COMPETITIONS = Competitions.COMPETITIONS;

    /**
     * The table <code>${table.prefix}fish</code>.
     */
    public final Fish FISH = Fish.FISH;

    /**
     * The table <code>${table.prefix}fish_log</code>.
     */
    public final FishLog FISH_LOG = FishLog.FISH_LOG;

    /**
     * The table <code>${table.prefix}transactions</code>.
     */
    public final Transactions TRANSACTIONS = Transactions.TRANSACTIONS;

    /**
     * The table <code>${table.prefix}users</code>.
     */
    public final Users USERS = Users.USERS;

    /**
     * The table <code>${table.prefix}users_sales</code>.
     */
    public final UsersSales USERS_SALES = UsersSales.USERS_SALES;

    /**
     * No further instances allowed
     */
    private DefaultSchema() {
        super("", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            Competitions.COMPETITIONS,
            Fish.FISH,
            FishLog.FISH_LOG,
            Transactions.TRANSACTIONS,
            Users.USERS,
            UsersSales.USERS_SALES
        );
    }
}