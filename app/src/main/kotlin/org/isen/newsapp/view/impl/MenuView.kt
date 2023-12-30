package org.isen.newsapp.view.impl

import org.apache.logging.log4j.kotlin.logger
import org.isen.newsapp.controller.MenuController
import org.isen.newsapp.controller.NewsController
import org.isen.newsapp.controller.SourcesController
import org.isen.newsapp.view.INewsView
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.FocusEvent
import java.awt.event.FocusListener
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
                    this.preferredSize = Dimension(1000,800)
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
    private fun resetEverythingParameters(){
        countryList = null
        categoryList = null
        languageList = null
        keyword = null
        fromDate = null
        toDate = null
        sortBy = null
    }
    private fun createKeywordField(){
        if (keyword == null) {
            keyword = JTextField("Mot clé").apply {
                preferredSize = Dimension(100, 30)
                addFocusListener(PlaceholderFocusListener("Mot clé", this))
                addActionListener(this@MenuView)
            }
        }
    }
    private fun createFromDateField(){
        if (fromDate == null){
            //format = yyyy-mm-dd
            fromDate = JTextField("yyyy-mm-dd").apply {
                preferredSize = Dimension(100, 30)
                addFocusListener(PlaceholderFocusListener("yyyy-mm-dd", this))
                addActionListener(this@MenuView)
            }
        }
    }
    private fun createToDateField(){
        if (toDate == null){
            //format = yyyy-mm-dd
            toDate = JTextField("yyyy-mm-dd").apply {
                preferredSize = Dimension(100, 30)
                addFocusListener(PlaceholderFocusListener("yyyy-mm-dd", this))
                addActionListener(this@MenuView)
            }
        }
    }

    private fun createLanguageList(): JComboBox<String>{
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
        return languageList!!
    }
    private fun createCountryList(): JComboBox<String>{
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
        return countryList!!
    }
    private fun createCategoryList(): JComboBox<String>{
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
        return categoryList!!
    }
    private fun createSortByList(): JComboBox<String>{
        if (sortBy == null){
            sortBy = JComboBox<String>().apply {
                addItem("relevancy")
                addItem("popularity")
                addItem("publishedAt")
                addActionListener(this@MenuView)
            }
        }
        return sortBy!!
    }

    private fun createEverythingParameters(): JPanel{
        resetEverythingParameters()
        dynamicFieldsPanel.removeAll()
        createLanguageList()
        createKeywordField()
        createFromDateField()
        createToDateField()
        createSortByList()
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
        resetEverythingParameters()
        dynamicFieldsPanel.removeAll()
        createCountryList()
        createCategoryList()
        createLanguageList()
        createKeywordField()
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
        resetEverythingParameters()
        dynamicFieldsPanel.removeAll()
        createCountryList()
        createCategoryList()
        createLanguageList()
        dynamicFieldsPanel.add(JLabel("Pays"))
        dynamicFieldsPanel.add(countryList)
        dynamicFieldsPanel.add(JLabel("Catégorie"))
        dynamicFieldsPanel.add(categoryList)
        dynamicFieldsPanel.add(JLabel("Langue"))
        dynamicFieldsPanel.add(languageList)
        return dynamicFieldsPanel
    }

    class PlaceholderFocusListener(private val placeholder: String, private val textField: JTextField) : FocusListener {
        override fun focusGained(e: FocusEvent) {
            if (textField.text == placeholder) {
                textField.text = ""
                textField.foreground = Color.BLACK
            }
        }

        override fun focusLost(e: FocusEvent) {
            if (textField.text.isEmpty()) {
                textField.text = placeholder
                textField.foreground = Color.GRAY
            }
        }
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

    fun checkdateinput(date: String): Boolean{
        if(date == "yyyy-mm-dd") return true
        if (date == "") return true
        val regex = Regex(pattern = """\d{4}-\d{2}-\d{2}""")
        return regex.matches(date)
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
            }
        } else {
            logger().error("evt is null")
        }
    }

    override fun actionPerformed(e: ActionEvent?) {
        if (e != null && frame!=null) {
            if (e.source is JComboBox<*>) {
                logger().info("actionPerformed: ${(e.source as JComboBox<*>).selectedItem}")
                if((e.source as JComboBox<*>).selectedItem.toString() == "Sources" || (e.source as JComboBox<*>).selectedItem.toString() == "Headlines" || (e.source as JComboBox<*>).selectedItem.toString() == "Everything" ){
                    currentRequestType = (e.source as JComboBox<*>).selectedItem.toString()
                }
                when ((e.source as JComboBox<*>).selectedItem) {
                    "Sources" ->  setDynamicParametersPanel(createSourcesParameters())
                    "Headlines" -> setDynamicParametersPanel(createHeadlinesParameters())
                    "Everything" -> setDynamicParametersPanel(createEverythingParameters())
                }
                frame.pack()
            }
            if (e.source is JButton) {
                logger().info("actionPerformed: ${e.actionCommand}")
                if (e.actionCommand == "Valider") {
                    // Récupérer les valeurs sélectionnées dans les combobox
                    var country = countryList?.selectedItem.toString() ?: ""
                    var category = categoryList?.selectedItem.toString() ?: ""
                    var language = languageList?.selectedItem.toString() ?: ""
                    var keyword = keyword?.text ?: ""
                    var from = fromDate?.text ?: ""
                    if (checkdateinput(from) == false){
                        JOptionPane.showMessageDialog(frame, "La date de début n'est pas au bon format (yyyy-mm-dd)")
                        return
                    }
                    var to = toDate?.text ?: ""
                    if (checkdateinput(to) == false){
                        JOptionPane.showMessageDialog(frame, "La date de fin n'est pas au bon format (yyyy-mm-dd)")
                        return
                    }
                    val sort = sortBy?.selectedItem.toString() ?: ""
                    if (country == "all") country = ""
                    if(keyword == "Mot clé") keyword = ""
                    if (from == "yyyy-mm-dd") from = ""
                    if (to == "yyyy-mm-dd") to = ""
                    if (category == "all") category = ""
                    if (language == "all") language = ""
                    println( currentRequestType )

                    if (currentRequestType == "Sources") {
                        if(sourceController.checkRequest(country, category, language) == false){
                            JOptionPane.showMessageDialog(frame, "Les paramètres ne sont pas valides")
                            return
                        }else{
                            sourceController.getSources("country=$country&category=$category&language=$language", "d085fa05e7ca462c8bb0e770ec30f41e")
                            println( "country=$country&category=$category&language=$language")
                        }
                    }
                    if (currentRequestType == "Everything") {
                        if(newsController.checkRequestEverything(keyword, from, to, sort, language) == false){
                            JOptionPane.showMessageDialog(frame, "Les paramètres ne sont pas valides")
                            return
                        }else{
                            newsController.getnewsall("q=$keyword&from=$from&to=$to&sortBy=$sort&language=$language", "d085fa05e7ca462c8bb0e770ec30f41e")
                            println( "q=$keyword&from=$from&to=$to&sortBy=$sort&language=$language")
                        }
                    }
                    if (currentRequestType == "Headlines") {
                        if(newsController.checkRequestHeadlines(keyword,country, category, language) == false){
                            JOptionPane.showMessageDialog(frame, "Les paramètres ne sont pas valides")
                            return
                        }else{
                            newsController.getnewsheadlines("country=$country&category=$category&language=$language&q=$keyword", "d085fa05e7ca462c8bb0e770ec30f41e")
                            println( "country=$country&category=$category&language=$language&q=$keyword")
                        }
                    }
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