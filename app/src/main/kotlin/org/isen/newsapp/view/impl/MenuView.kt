package org.isen.newsapp.view.impl

import org.apache.logging.log4j.kotlin.logger
import org.isen.newsapp.controller.MenuController
import org.isen.newsapp.controller.NewsController
import org.isen.newsapp.controller.SourcesController
import org.isen.newsapp.view.INewsView
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.beans.PropertyChangeEvent
import javax.swing.*


//classe principlae de l'app qui contient les 3 autres vues et qui permet de naviguer entre elles ainsi que de donner les paramètres de connexion à l'api
class MenuView (val controller: MenuController, val sourceController: SourcesController, val newsController: NewsController, title: String="News App"): INewsView, ActionListener {
    companion object logging

    private var frame: JFrame
    private var countryList: JComboBox<String>? = null
    private var categoryList: JComboBox<String>? = null
    private var languageList: JComboBox<String>? = null
    private var keyword: JTextField? = null
    private var dynamicFieldsPanel: JPanel
    private var fromDate: JTextField? = null
    private var toDate: JTextField? = null
    private var sortBy: JComboBox<String>? = null
    var currentRequestType = ""

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
        dynamicFieldsPanel = JPanel()
        dynamicFieldsPanel.layout = FlowLayout()
        currentRequestType = "Headlines"
        setDynamicParametersPanel(createHeadlinesParameters())
    }
    private fun makeGUI(): JPanel {
        val contentPane = JPanel()
        contentPane.layout = BorderLayout()

        val requestTypeComboBox = createRequestTypeComboBox()
        if (requestTypeComboBox != null) {
            contentPane.add(requestTypeComboBox, BorderLayout.NORTH)
        }

        val dynamicPanel = dynamicFieldsPanel
        if (dynamicPanel != null) {
            contentPane.add(dynamicPanel, BorderLayout.CENTER)
        }

        val button = createButton()
        if (button != null) {
            contentPane.add(button, BorderLayout.SOUTH)
        }

        return contentPane
    }

    private fun createRequestTypeComboBox(): JPanel {
        val contentPane = JPanel()
        contentPane.layout = BorderLayout()
        contentPane.add(JLabel("Type de requête"), BorderLayout.NORTH)
        val requestTypeList = JComboBox<String>().apply {
            addItem("Headlines")
            addItem("Everything")
            addItem("Sources")
            // Ajouter d'autres types de requête au besoin
            addActionListener(this@MenuView)
        }
        //set default value
        requestTypeList.selectedIndex = 0
        requestTypeList.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
        contentPane.add(requestTypeList, BorderLayout.CENTER)
        return contentPane
    }
    private fun createEverythingParameters(): JPanel{
// ... (autres initialisations)

        // labels + comboboxes
        dynamicFieldsPanel.removeAll()

        // Initialiser les combobox si elles ne le sont pas déjà
        if (languageList == null) {
            languageList = JComboBox<String>().apply {
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
        }

        if (keyword == null){
            keyword = JTextField().apply {
                addActionListener(this@MenuView)
            }
        }

        if (fromDate == null){
            fromDate = JTextField().apply {
                addActionListener(this@MenuView)
            }
        }
        if (toDate == null){
            //format = yyyy-mm-dd
            toDate = JTextField().apply {
                addActionListener(this@MenuView)
            }
        }

        if (sortBy == null){
            sortBy = JComboBox<String>().apply {
                addItem("relevancy")
                addItem("popularity")
                addItem("publishedAt")
                addActionListener(this@MenuView)
            }
        }

        dynamicFieldsPanel.add(JLabel("Langue"))
        dynamicFieldsPanel.add(languageList)
        dynamicFieldsPanel.add(JLabel("Mot clé"))
        dynamicFieldsPanel.add(keyword)
        dynamicFieldsPanel.add(JLabel("Date de début"))
        dynamicFieldsPanel.add(fromDate)
        dynamicFieldsPanel.add(JLabel("Date de fin"))
        dynamicFieldsPanel.add(toDate)
        dynamicFieldsPanel.add(JLabel("Trier par"))
        dynamicFieldsPanel.add(sortBy)
        return dynamicFieldsPanel
    }
    private fun createHeadlinesParameters(): JPanel{
        // ... (autres initialisations)

        // labels + comboboxes
        dynamicFieldsPanel.removeAll()

        // Initialiser les combobox si elles ne le sont pas déjà
        if (countryList == null) {
            countryList = JComboBox<String>().apply {
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
        }

        if (categoryList == null) {
            categoryList = JComboBox<String>().apply {
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
        }

        if (languageList == null) {
            languageList = JComboBox<String>().apply {
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
        }

        if (keyword == null){
            keyword = JTextField().apply {
                addActionListener(this@MenuView)
            }
        }

        dynamicFieldsPanel.add(JLabel("Pays"))
        dynamicFieldsPanel.add(countryList)
        dynamicFieldsPanel.add(JLabel("Catégorie"))
        dynamicFieldsPanel.add(categoryList)
        dynamicFieldsPanel.add(JLabel("Langue"))
        dynamicFieldsPanel.add(languageList)
        dynamicFieldsPanel.add(JLabel("Mot clé"))
        dynamicFieldsPanel.add(keyword)
        return dynamicFieldsPanel
    }
    private fun createSourcesParameters(): JPanel {
        // ... (autres initialisations)

        // labels + comboboxes
        dynamicFieldsPanel.removeAll()

        // Initialiser les combobox si elles ne le sont pas déjà
        if (countryList == null) {
            countryList = JComboBox<String>().apply {
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
        }

        if (categoryList == null) {
            categoryList = JComboBox<String>().apply {
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
        }

        if (languageList == null) {
            languageList = JComboBox<String>().apply {
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
        }

        dynamicFieldsPanel.add(JLabel("Pays"))
        dynamicFieldsPanel.add(countryList)
        dynamicFieldsPanel.add(JLabel("Catégorie"))
        dynamicFieldsPanel.add(categoryList)
        dynamicFieldsPanel.add(JLabel("Langue"))
        dynamicFieldsPanel.add(languageList)
        return dynamicFieldsPanel
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
        if (e != null && frame!=null) {
            //displays the correct view depending on the selected value in the menu
            if (e.source is JComboBox<*>) {
                logger().info("actionPerformed: ${(e.source as JComboBox<*>).selectedItem}")
                currentRequestType = (e.source as JComboBox<*>).selectedItem.toString()
                when ((e.source as JComboBox<*>).selectedItem) {
                    "Sources" ->  setDynamicParametersPanel(createSourcesParameters())
                    "Headlines" -> setDynamicParametersPanel(createHeadlinesParameters())
                    "Everything" -> setDynamicParametersPanel(createEverythingParameters())
                    // Ajoutez d'autres cas pour les autres types de requête
                }
                frame.pack()
            }
            // launches the request
            if (e.source is JButton) {
                logger().info("actionPerformed: ${e.actionCommand}")
                if (e.actionCommand == "Valider") {
                    // Récupérer les valeurs sélectionnées dans les combobox
                    var country = countryList?.selectedItem.toString() ?: ""
                    var category = categoryList?.selectedItem.toString() ?: ""
                    var language = languageList?.selectedItem.toString() ?: ""
                    val keyword = keyword?.text ?: ""
                    val from = fromDate?.text ?: ""
                    val to = toDate?.text ?: ""
                    val sort = sortBy?.selectedItem.toString() ?: ""
                    if (country == "all") country = ""
                    if (category == "all") category = ""
                    if (language == "all") language = ""
                    // Appeler la méthode dans le contrôleur avec les paramètres
                    println( currentRequestType )
                    if (currentRequestType == "Sources") {
                        sourceController.getSources("country=$country&category=$category&language=$language", "d085fa05e7ca462c8bb0e770ec30f41e")
                        println( "country=$country&category=$category&language=$language")
                    }
                    if (currentRequestType == "Everything") {
                        newsController.getnewsall("q=$keyword&from=$from&to=$to&sortBy=$sort&language=$language", "d085fa05e7ca462c8bb0e770ec30f41e")
                        println( "q=$keyword&from=$from&to=$to&sortBy=$sort&language=$language")
                    }
                    if (currentRequestType == "Headlines") {
                        newsController.getnewsheadlines("country=$country&category=$category&language=$language&q=$keyword", "d085fa05e7ca462c8bb0e770ec30f41e")
                        println( "country=$country&category=$category&language=$language&q=$keyword")
                    }
                    // Ajoutez d'autres conditions pour les autres types de requête
                }
            }
        }
    }
    private fun setDynamicParametersPanel(panel: JPanel) {
        // Replace only the dynamic parameters panel
        frame.contentPane.remove(dynamicFieldsPanel)
        dynamicFieldsPanel = panel
        frame.contentPane.add(dynamicFieldsPanel, BorderLayout.CENTER)
        frame.pack()
    }
}