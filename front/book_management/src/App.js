import {Global} from "@emotion/react";
import {Reset} from "./styles/Global/reset";
import Login from "./pages/Login/Login";
import {Route, Routes} from "react-router-dom";
import Register from "./pages/Register/Register";
import Main from "./pages/main/Main";
import AuthRoute from "./components/Routes/AuthRoute/AuthRoute";


function App() {

  return (
    <>
      <Global styles={ Reset }></Global>
        <Routes>
            <Route path={"/login"} element={<Login/>} />
            <Route path={"/register"} element={<Register/>} />
            <Route path={"/"} element={
                <AuthRoute authenticated={false} element={<Main/>}/>
            } />

            {/*서브 라우터*/}
            {/*<Route exact path={"/callback"} Component={Callback} />*/}
            {/*<Route exact path={"/promise"} Component={PromiseStudy} />*/}
        </Routes>
    </>
  );
}

export default App;
