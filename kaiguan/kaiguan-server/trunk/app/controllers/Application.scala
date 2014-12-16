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

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }
  
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
    
    this.switchStatus = statusForm.bindFromRequest.get.toBoolean
    
    Logger debug "switchStatus = " + switchStatus
    
    Ok
    
  } // end setStatus
  

} // end Application