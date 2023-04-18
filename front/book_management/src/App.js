import {Global} from "@emotion/react";
import {Reset} from "./styles/Global/reset";
import Login from "./pages/Login/Login";
import {Route, Routes} from "react-router-dom";
import Register from "./pages/Register/Register";


function App() {

  return (
    <>
      <Global styles={ Reset }></Global>
        <Routes>
            <Route exact path={"/login"} Component={Login} />
            <Route exact path={"/register"} Component={Register} />
        </Routes>
    </>
  );
}

export default App;
