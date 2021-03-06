import Mock from "mockjs";

const departments = [
  {
    "departmentId": 1,
    "name": "董事长",
    "description": "董事长",
    "departmentCode": "President",
    "sort": 1
  },
  {
    "departmentId": 2,
    "name": "人事",
    "description": "人事",
    "departmentCode": "HR",
    "sort": 1
  }
]

export default [
  {
    url: '/department/departments',
    type: 'get',
    response: _ => {
      return {
        code: 0,
        list: departments
      }
    }
  },
  {
    url: '/department/add',
    type: 'post',
    response: {
      code: 0
    }
  },
  {
    url: '/department/update',
    type: 'post',
    response: {
      code: 0
    }
  },
  {
    url: '/department/del/[A-Za-z0-9]',
    type: 'get',
    response:{
      code:0
    }
  }
]
