package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.libs.json.Json
import play.api.libs.json.Json._

object Application extends Controller {
  
  // define the switch status
  var switchStatus = false
  
  // define the POST form
  val statusForm = Form(
    single(
      "status" -> text
    )
  )
  

  /**
   * index
   */
  def index = Action {
    
    val lockedString = if (switchStatus) "LOCKED" else "UNLOCKED"
    
    Ok(views.html.index("The door is " + lockedString))
        
  } // end index
  
  
  /**
   * getStatus
   */
  def getStatus = Action {
    
    Ok(toJson(Map("status" -> switchStatus)))
    
  } // end getStatus
  
  
  /**
   * setStatus
   */
  def setStatus = Action { implicit request =>
    
    switchStatus = statusForm.bindFromRequest.get.toBoolean
    
    Logger debug "switchStatus = " + switchStatus
    
    Ok
    
  } // end setStatus
  

} // end Application