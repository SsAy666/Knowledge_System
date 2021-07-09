import request from '@/utils/request'
const baseURL = 'http://localhost:3398/'
//const baseURL = 'http://121.4.182.177:3398/'

export default {
  onsubmit: data => request({ url: baseURL + 'admin/addUser', method: 'post', data }),
  updateUser: data => request({ url: baseURL + 'admin/updateUser', method: 'post', data }),
  delUser: data => request({ url: baseURL + 'admin/delUser?' + data, method: 'get' }),
  queryUser: data => request({ url: baseURL + 'admin/queryUser', method: 'post', data }),
  updatePwd: data => request({ url: baseURL + 'user/updatePwd', method: 'post', data }),
  addKnowledgeContent: data => request({ url: 'knowledge/content/addKnowledgeContent', method: 'post', data }),
  addKnowledgeTree: data => request({ url: 'knowledge/tree/addKnowledgeTree', method: 'post', data }),
  updateKnowledgeContent: data => request({ url: 'knowledge/content/updateKnowledgeContent', method: 'post', data }),
  updateKnowledgeTree: data => request({ url: 'knowledge/tree/updateKnowledgeTree', method: 'post', data }),
  queryKnowledgeContent: data => request({ url: 'knowledge/content/queryKnowledgeContent', method: 'post', data }),
  queryKnowledgeTree: data => request({ url: 'knowledge/tree/queryKnowledgeTree', method: 'get', data }),
  queryHistoryRecords: data => request({ url: 'knowledge/content/queryHistoryRecords', method: 'get', data }),
  delKnowledgeContent: data => request({ url: 'knowledge/content/delKnowledgeContent?' + data, method: 'get' }),
  delKnowledgeTree: data => request({ url: 'knowledge/tree/delKnowledgeTree?' + data, method: 'get' }),
}
