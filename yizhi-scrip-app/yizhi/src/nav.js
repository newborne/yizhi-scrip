import React from 'react';
import {NavigationContainer} from '@react-navigation/native';
import {createStackNavigator} from '@react-navigation/stack';
import {inject, observer} from 'mobx-react';
import Login from '@src/page/0_login/login';
import UserInfo from '@src/page/0_login/userInfo';
import Tabbar from '@src/tabbar';
import Demo from '@src/page/demo';

const Stack = createStackNavigator();
@inject('RootStore')
@observer
class Nav extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      initialRouteName: this.props.RootStore.token ? 'Tabbar' : 'Login',
    };
  }
  render() {
    const {initialRouteName} = this.state;
    return (
      <NavigationContainer>
        <Stack.Navigator headerMode="none" initialRouteName={'UserInfo'}>
          <Stack.Screen name="Demo" component={Demo} />
          <Stack.Screen name="Login" component={Login} />
          <Stack.Screen name="UserInfo" component={UserInfo} />
          <Stack.Screen name="Tabbar" component={Tabbar} />
        </Stack.Navigator>
      </NavigationContainer>
    );
  }
}
export default Nav;
