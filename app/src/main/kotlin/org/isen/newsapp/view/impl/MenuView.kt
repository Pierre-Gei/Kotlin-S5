package org.isen.newsapp.view.impl

import org.apache.logging.log4j.kotlin.logger
import org.isen.newsapp.controller.MenuController
import org.isen.newsapp.view.INewsView
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.beans.PropertyChangeEvent
import javax.swing.*


//classe principlae de l'app qui contient les 3 autres vues et qui permet de naviguer entre elles ainsi que de donner les paramètres de connexion à l'api
class MenuView (val controller: MenuController, title: String="News App"): INewsView, ActionListener {
    companion object logging

    private val frame : JFrame
    init {
        frame = JFrame().apply {
            isVisible = false
            contentPane = makeGUI()
            defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
            this.title = title
            this.preferredSize = Dimension(800, 600)
            this.pack()
        }
        this.controller.registerViewToMenu(this)
    }

    private fun makeGUI(): JPanel {
        val contentPane = JPanel()
        contentPane.layout = BorderLayout()
        contentPane.add(createRequestTypeComboBox(), BorderLayout.NORTH)
        contentPane.add(createButton(), BorderLayout.SOUTH)
        return contentPane
    }

    //création du menu de navigation
    private fun createRequestTypeComboBox(): JPanel {
        val contentPane = JPanel()
        contentPane.layout = BorderLayout()
        contentPane.add(JLabel("Type de requête"), BorderLayout.NORTH)
        val requestTypeList = JComboBox<String>().apply {
            addItem("Sources")
            addItem("Headlines")
            addItem("Everything")
            addActionListener(this@MenuView)
        }
        requestTypeList.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
        contentPane.add(requestTypeList, BorderLayout.CENTER)
        return contentPane
    }

    //création des paramètres de la requête sources
    private fun createSourcesParameters(): JPanel {
        val contentPane = JPanel()
        contentPane.layout = FlowLayout()
        val countryList = JComboBox<String>().apply {
            //possibles values for the country parameter : ae ar at au be bg br ca ch cn co cu cz de eg fr gb gr hk hu id ie il in it jp kr lt lv ma mx my ng nl no nz ph pl pt ro rs ru sa se sg si sk th tr tw ua us ve za + all
            addItem("all")
            addItem("ae")
            addItem("ar")
            addItem("at")
            addItem("au")
            addItem("be")
            addItem("bg")
            addItem("br")
            addItem("ca")
            addItem("ch")
            addItem("cn")
            addItem("co")
            addItem("cu")
            addItem("cz")
            addItem("de")
            addItem("eg")
            addItem("fr")
            addItem("gb")
            addItem("gr")
            addItem("hk")
            addItem("hu")
            addItem("id")
            addItem("ie")
            addItem("il")
            addItem("in")
            addItem("it")
            addItem("jp")
            addItem("kr")
            addItem("lt")
            addItem("lv")
            addItem("ma")
            addItem("mx")
            addItem("my")
            addItem("ng")
            addItem("nl")
            addItem("no")
            addItem("nz")
            addItem("ph")
            addItem("pl")
            addItem("pt")
            addItem("ro")
            addItem("rs")
            addItem("ru")
            addItem("sa")
            addItem("se")
            addItem("sg")
            addItem("si")
            addItem("sk")
            addItem("th")
            addItem("tr")
            addItem("tw")
            addItem("ua")
            addItem("us")
            addItem("ve")
            addItem("za")
            addActionListener(this@MenuView)
        }
        val categoryList = JComboBox<String>().apply {
            //possible values for the category parameter : business entertainment general health science sports technology + all
            addItem("all")
            addItem("business")
            addItem("entertainment")
            addItem("general")
            addItem("health")
            addItem("science")
            addItem("sports")
            addItem("technology")
            addActionListener(this@MenuView)
        }
        val languageList = JComboBox<String>().apply {
            //possible values for the language parameter : ar de en es fr he it nl no pt ru se ud zh + all
            addItem("all")
            addItem("ar")
            addItem("de")
            addItem("en")
            addItem("es")
            addItem("fr")
            addItem("he")
            addItem("it")
            addItem("nl")
            addItem("no")
            addItem("pt")
            addItem("ru")
            addItem("se")
            addItem("ud")
            addItem("zh")
            addActionListener(this@MenuView)
        }
        //add labels
        countryList.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
        categoryList.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
        languageList.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
        countryList.setSelectedIndex(0)
        categoryList.setSelectedIndex(0)
        languageList.setSelectedIndex(0)
        //labels + comboboxes
        contentPane.add(JLabel("Pays"))
        contentPane.add(countryList)
        contentPane.add(JLabel("Catégorie"))
        contentPane.add(categoryList)
        contentPane.add(JLabel("Langue"))
        contentPane.add(languageList)
        return contentPane
    }


    //création du bouton de validation
    private fun createButton(): JPanel {
        val contentPane = JPanel()
        contentPane.layout = BorderLayout()
        val button = JButton("Valider")
        button.addActionListener(this)
        contentPane.add(button, BorderLayout.CENTER)
        return contentPane
    }


    override fun display() {
        frame.isVisible = true
    }

    override fun close() {
        this.controller.closeview()
    }

    override fun propertyChange(evt: PropertyChangeEvent?) {
        if (evt != null) {
            if (evt.newValue is String) {
                logger().info("NewsView: ${evt.newValue}")
                println( evt.newValue)
            }
        } else {
            logger().error("evt is null")
        }
    }

    override fun actionPerformed(e: ActionEvent?) {
        if (e != null) {
            // Selection of the type of request
            if (e.source is JComboBox<*>) {
                logger().info("actionPerformed: ${e.actionCommand}")
                when ((e.source as JComboBox<*>).selectedItem) {
                    "Sources" -> {
                        val sourcesParameters = createSourcesParameters()
                        frame.contentPane.add(sourcesParameters, BorderLayout.CENTER)
                    }

                    "Headlines" -> {
                        // Handle "Headlines" case
                    }

                    "Everything" -> {
                        // Handle "Everything" case
                    }
                }
            }
            //observation des changements de paramètres pour les garder a jour et les envoyer a la requête lors de la validation
            if (e.source is JComboBox<*>) {
                val selctedComboBox = e.source as JComboBox<*>
                when (selctedComboBox.selectedItem) {
                    "Sources" -> {
                        val country = selctedComboBox.selectedItem
                        val category = selctedComboBox.selectedItem
                        val language = selctedComboBox.selectedItem
                        controller.setParameters(country.toString(), category.toString(), language.toString())
                    }

                    "Headlines" -> {
                        // Handle "Headlines" case
                    }

                    "Everything" -> {
                        // Handle "Everything" case
                    }
                }
            }
        }
    }

}