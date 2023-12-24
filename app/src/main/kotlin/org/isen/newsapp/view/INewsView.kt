package org.isen.newsapp.view

import java.beans.PropertyChangeListener

interface INewsView: PropertyChangeListener {
    fun display()
    fun close()
}