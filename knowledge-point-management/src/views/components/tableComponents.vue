<template>
  <el-table
    :data="tableData"
    style="width: 100%"
    @row-click="rowClick"
  >

    <el-table-column
      v-for="(item, index) in tableColumns"
      :key="index"
      :prop="item.prop"
      :label="item.label"
      :width="item.width || ''"
      :fixed="item.fixed"
      :align="item.align || 'center'"
    >
      <template slot-scope="scope">
        <slot v-if="item.operation" :scope="scope" name="operation" />
        <span v-else>{{ scope.row[item.prop] }}</span>
      </template>
    </el-table-column>

  </el-table>
</template>

<script>
export default {
  name: 'TableComponents',
  props: {
    tableData: {
      type: Array,
      required: true,
      default: () => []
    },
    tableColumns: {
      type: Array,
      required: true,
      default: () => []
    },
    tableConfig: {
      type: Object,
      default: () => ({})
    }
  },

  methods: {
    rowClick(row, column, event) {
      this.$emit('rowClick', { row, column, event })
    }
  }

}
</script>

<style scoped>

</style>
