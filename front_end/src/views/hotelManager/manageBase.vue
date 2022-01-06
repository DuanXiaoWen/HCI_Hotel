<template>
    <div class="manageBase-wrapper">
        <a-tabs v-model="tabKey" @change="handleChange">
            <a-tab-pane  tab="酒店管理" key="1">
                <manageHotel></manageHotel>
            </a-tab-pane>
            <a-tab-pane  tab="优惠管理" key="2">
                <manageWebsiteMarket></manageWebsiteMarket>
            </a-tab-pane>
        </a-tabs>
    </div>
</template>
<script>
import manageHotel from "./manageHotel";
import manageWebsiteMarket from "./manageWebsiteMarket";
import {mapActions, mapMutations} from "vuex";
export default {
    name: 'manageBase',
    data(){
        return {
            formLayout: 'horizontal',
            tabKey: '1',
            pagination: {},
            data: [],
            form: this.$form.createForm(this, { name: 'manageBase' }),
        }
    },
    components: {
        manageHotel,
        manageWebsiteMarket
    },
    methods: {
        ...mapMutations([
            'set_activeHotelId',
        ]),
        ...mapActions([
            'getHotelCoupon',
        ]),
        async handleChange() {
            if (this.tabKey==='2') {
                this.set_activeHotelId(-1);
                await this.getHotelCoupon();
            }
        }
    }
}
</script>
<style scoped lang="less">
    .manageBase-wrapper {
        padding: 50px;
        .chart {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-top: 20px
        }
    }
</style>
<style lang="less">
    .manageBase-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">

</style>