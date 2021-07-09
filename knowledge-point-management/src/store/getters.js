const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.user.token,
  userId: state => state.user.userId,
  roles: state => state.user.roles,
  username: state => state.user.username
}
export default getters
