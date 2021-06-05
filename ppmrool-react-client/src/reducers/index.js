import { combineReducers } from "redux";
import errorReducer from "./errorReducer";
import projectsReducer from "./projectsReducer";

export default combineReducers({
    errors: errorReducer,
    project: projectsReducer
});