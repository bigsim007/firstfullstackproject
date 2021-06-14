import { combineReducers } from "redux";
import backlogReducer from "./backlogReducer";
import errorReducer from "./errorReducer";
import projectsReducer from "./projectsReducer";

export default combineReducers({
    errors: errorReducer,
    project: projectsReducer,
    backlog: backlogReducer
});