package com.example;

import org.hibernate.Session;

import com.example.util.HibernateUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Session session = HibernateUtil.getSession();
    }
}
